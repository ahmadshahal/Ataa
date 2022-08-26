package com.hero.ataa.shared

sealed class UiEvent {
    data class ShowSnackBar(
        val message: UiText,
        val action: UiText = UiText.DynamicText("")
    ) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    data class SendUrlIntent(val url: String) : UiEvent()
    object PopBackStack : UiEvent()
}