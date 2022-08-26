package com.hero.ataa.ui.screens.search_screen

sealed class AllProjectsUiState {
    object Success : AllProjectsUiState()
    object Loading : AllProjectsUiState()
    object Error : AllProjectsUiState()
}