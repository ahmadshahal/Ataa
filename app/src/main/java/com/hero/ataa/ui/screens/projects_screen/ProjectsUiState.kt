package com.hero.ataa.ui.screens.projects_screen

import com.hero.ataa.domain.models.Project


sealed class ProjectsUiState {
    data class Success(val projects: List<Project>) : ProjectsUiState()
    object Loading : ProjectsUiState()
    object Error : ProjectsUiState()
}
