package com.hero.ataa.ui.screens.verification_screen

sealed class VerifyUiState {
    object Loading : VerifyUiState()
    object Initial : VerifyUiState()
}
