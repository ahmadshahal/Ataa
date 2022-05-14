package com.hero.ataa.shared

sealed class UiEvent {
    data class ShowSnackBar(val message: String, val action: String = "") : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    object PopBackStack : UiEvent()
}