package com.hero.ataa.ui.screens.projects_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.domain.use_cases.GetProjectsUseCase
import com.hero.ataa.shared.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(val getProjectsUseCase: GetProjectsUseCase) : ViewModel() {
    private val _uiState = mutableStateOf<ProjectsUiState>(ProjectsUiState.Loading)
    val uiState: State<ProjectsUiState>
        get() = _uiState

    init {
        getProjects()
    }

    private fun getProjects() {
        viewModelScope.launch {
            getProjectsUseCase(
                categoryArg = "edu",
            ).collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        _uiState.value = ProjectsUiState.Loading
                    }
                    is DataState.Error -> {
                        _uiState.value = ProjectsUiState.Error
                    }
                    is DataState.Success -> {
                        _uiState.value = ProjectsUiState.Success(projects = dataState.data)
                    }
                    else -> Unit
                }
            }
        }
    }
}