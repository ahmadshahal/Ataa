package com.hero.ataa.shared

sealed class UiEvent {
    data class ShowSnackBar(
        val message: UiText,
        val action: UiText = UiText.DynamicText("")
    ) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    object PopBackStack : UiEvent()
}