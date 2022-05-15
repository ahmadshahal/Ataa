package com.hero.ataa.ui.screens.register_screen


sealed class RegisterUiState {
    object Initial : RegisterUiState()
    object Loading : RegisterUiState()
}
