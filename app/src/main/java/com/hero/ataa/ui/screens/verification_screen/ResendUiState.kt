package com.hero.ataa.ui.screens.verification_screen

sealed class ResendUiState {
    object Loading : ResendUiState()
    object Initial : ResendUiState()
}
