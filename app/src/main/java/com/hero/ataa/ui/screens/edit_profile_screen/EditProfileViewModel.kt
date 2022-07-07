package com.hero.ataa.ui.screens.edit_profile_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hero.ataa.shared.UiText
import com.hero.ataa.utils.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor() : ViewModel() {
    val fullNameFieldText = mutableStateOf("")
    val passwordFieldText = mutableStateOf("")
    val confirmPasswordFieldText = mutableStateOf("")
    val phoneNumberFieldText = mutableStateOf("")

    val passwordVisible = mutableStateOf(false)
    val confirmPasswordVisible = mutableStateOf(false)

    val isErrorNameField = mutableStateOf(false)
    val isErrorPasswordField = mutableStateOf(false)
    val isErrorConfirmPasswordField = mutableStateOf(false)
    val isErrorPhoneNumberField = mutableStateOf(false)

    val nameFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))
    val passwordFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))
    val confirmPasswordFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))
    val phoneNumberFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    val showDialog = mutableStateOf(false)
    val selectedCountry = mutableStateOf(Country("sy", "963", "Syrian Arab Republic"))

}