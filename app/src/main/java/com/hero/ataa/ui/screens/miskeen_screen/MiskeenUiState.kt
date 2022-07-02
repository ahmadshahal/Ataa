package com.hero.ataa.ui.screens.miskeen_screen

sealed class MiskeenUiState {
    object Loading : MiskeenUiState()
    object Error : MiskeenUiState()
    data class Success(val miskeenValue: Int) : MiskeenUiState()
}
