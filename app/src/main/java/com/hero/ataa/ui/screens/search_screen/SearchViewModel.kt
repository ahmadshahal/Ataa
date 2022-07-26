package com.hero.ataa.ui.screens.search_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.domain.models.Project
import com.hero.ataa.domain.use_cases.GetAllProjectsUseCase
import com.hero.ataa.shared.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAllProjectsUseCase: GetAllProjectsUseCase
) : ViewModel() {

    var projects = listOf<Project>()

    private val _uiState = mutableStateOf<AllProjectsUiState>(AllProjectsUiState.Loading)
    val uiState: State<AllProjectsUiState>
        get() = _uiState

    val searchFieldText = mutableStateOf("")

    val searchResults = derivedStateOf {
        projects.filter { project ->
            project.title.contains(searchFieldText.value)
            || project.description.contains(searchFieldText.value)
            || project.location.contains(searchFieldText.value)
        }
    }

    init {
        getProjects()
    }

    fun getProjects() {
        viewModelScope.launch {
            getAllProjectsUseCase().collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        _uiState.value = AllProjectsUiState.Loading
                    }
                    is DataState.Error -> {
                        _uiState.value = AllProjectsUiState.Error
                    }
                    is DataState.Success -> {
                        projects = dataState.data
                        _uiState.value = AllProjectsUiState.Success
                    }
                    else -> Unit
                }
            }
        }
    }
}