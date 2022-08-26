package com.hero.ataa.ui.screens.beneficiary_screen


sealed class FormUiState {
    object Initial : FormUiState()
    object Loading : FormUiState()
}
