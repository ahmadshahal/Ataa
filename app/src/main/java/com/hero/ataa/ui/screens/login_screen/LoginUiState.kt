package com.hero.ataa.ui.screens.login_screen


sealed class LoginUiState {
    object Initial : LoginUiState()
    object Loading : LoginUiState()
}
