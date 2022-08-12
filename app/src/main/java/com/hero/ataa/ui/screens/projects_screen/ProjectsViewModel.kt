package com.hero.ataa.ui.screens.projects_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.domain.use_cases.GetProjectsUseCase
import com.hero.ataa.shared.Constants
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(
    private val getProjectsUseCase: GetProjectsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = mutableStateOf<ProjectsUiState>(ProjectsUiState.Loading)
    val uiState: State<ProjectsUiState>
        get() = _uiState

    private val _uiEvent: Channel<UiEvent> = Channel()
    val uiEvent: Flow<UiEvent>
        get() = _uiEvent.receiveAsFlow()

    init {
        getProjects()
    }

    fun getProjects() {
        viewModelScope.launch {
            getProjectsUseCase(
                categoryApiKey = savedStateHandle.get<String>(Constants.NavArgs.CATEGORY_API_KEY_KEY)!!,
            ).collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        _uiState.value = ProjectsUiState.Loading
                    }
                    is DataState.Error -> {
                        _uiEvent.send(UiEvent.ShowSnackBar(message = dataState.message))
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