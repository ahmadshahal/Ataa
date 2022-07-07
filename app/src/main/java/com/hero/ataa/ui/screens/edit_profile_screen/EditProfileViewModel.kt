package com.hero.ataa.ui.screens.edit_profile_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hero.ataa.R
import com.hero.ataa.shared.UiText
import com.hero.ataa.utils.Country
import com.hero.ataa.utils.Validation
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

    fun onSubmit() {
        val validateFullNameResult = validateFullName()
        val validatePasswordResult = validatePassword()
        val validateConfirmPasswordResult = validateConfirmPassword()
        val validatePhoneNumberResult = validatePhoneNumber()
        if (validateFullNameResult
            && validatePasswordResult && validateConfirmPasswordResult && validatePhoneNumberResult
        ) {
            // TODO.
        }
    }

    private fun validateFullName(): Boolean {
        return when (val fullNameResId: Int? =
            Validation.validateFullName(fullNameFieldText.value)
        ) {
            null -> {
                isErrorNameField.value = false
                nameFieldErrorMsg.value = UiText.DynamicText("")
                true
            }
            else -> {
                isErrorNameField.value = true
                nameFieldErrorMsg.value = UiText.ResourceText(fullNameResId)
                false
            }
        }
    }

    private fun validatePassword(): Boolean {
        return when (val passwordResId: Int? =
            Validation.validateRegisterPassword(passwordFieldText.value)
        ) {
            null -> {
                isErrorPasswordField.value = false
                passwordFieldErrorMsg.value = UiText.DynamicText("")
                true
            }
            else -> {
                isErrorPasswordField.value = true
                passwordFieldErrorMsg.value = UiText.ResourceText(passwordResId)
                false
            }
        }
    }

    private fun validateConfirmPassword(): Boolean {
        return when (val confirmPasswordResId: Int? =
            Validation.validateConfirmPassword(confirmPasswordFieldText.value)
        ) {
            null -> {
                if (confirmPasswordFieldText.value == passwordFieldText.value) {
                    isErrorConfirmPasswordField.value = false
                    confirmPasswordFieldErrorMsg.value = UiText.DynamicText("")
                    true
                } else {
                    isErrorConfirmPasswordField.value = true
                    confirmPasswordFieldErrorMsg.value =
                        UiText.ResourceText(R.string.passwords_not_identical)
                    false
                }
            }
            else -> {
                isErrorConfirmPasswordField.value = true
                confirmPasswordFieldErrorMsg.value = UiText.ResourceText(confirmPasswordResId)
                false
            }
        }
    }

    private fun validatePhoneNumber(): Boolean {
        return when (val phoneNumberResId: Int? =
            Validation.validatePhoneNumber(
                phoneNumber = phoneNumberFieldText.value,
                countryCode = selectedCountry.value.code
            )
        ) {
            null -> {
                isErrorPhoneNumberField.value = false
                phoneNumberFieldErrorMsg.value = UiText.DynamicText("")
                true
            }
            else -> {
                isErrorPhoneNumberField.value = true
                phoneNumberFieldErrorMsg.value = UiText.ResourceText(phoneNumberResId)
                false
            }
        }
    }
}