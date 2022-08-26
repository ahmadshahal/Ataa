package com.hero.ataa.ui.screens.sacrifice_project_screen


sealed class SacrificeUiState {
    object Loading : SacrificeUiState()
    object Error : SacrificeUiState()
    data class Success(val sacrificeValue: Int) : SacrificeUiState()
}
