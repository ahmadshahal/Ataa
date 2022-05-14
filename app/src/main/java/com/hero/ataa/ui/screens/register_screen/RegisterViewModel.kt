package com.hero.ataa.ui.screens.register_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hero.ataa.R
import com.hero.ataa.shared.UiText
import com.hero.ataa.utils.Validation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    val emailFieldText = mutableStateOf("")
    val nameFieldText = mutableStateOf("")
    val passwordFieldText = mutableStateOf("")
    val confirmPasswordFieldText = mutableStateOf("")
    val phoneNumberFieldText = mutableStateOf("")

    val isErrorEmailField = mutableStateOf(false)
    val isErrorNameField = mutableStateOf(false)
    val isErrorPasswordField = mutableStateOf(false)
    val isErrorConfirmPasswordField = mutableStateOf(false)
    val isErrorPhoneNumberField = mutableStateOf(false)

    val passwordVisible = mutableStateOf(false)
    val confirmPasswordVisible = mutableStateOf(false)

    val emailFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))
    val nameFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))
    val passwordFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))
    val confirmPasswordFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))
    val phoneNumberFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    fun onSubmit() {
        val validateEmailResult = validateEmail()
        val validateFullNameResult = validateFullName()
        val validatePasswordResult = validatePassword()
        val validateConfirmPasswordResult = validateConfirmPassword()
        val validatePhoneNumberResult = validatePhoneNumber()
        if (validateEmailResult && validateFullNameResult
            && validatePasswordResult && validateConfirmPasswordResult && validatePhoneNumberResult
        ) {
            // TODO: GO
        }
    }

    private fun validateEmail(): Boolean {
        return when (val emailResId: Int? = Validation.validateEmail(emailFieldText.value)
        ) {
            null -> {
                isErrorEmailField.value = false
                emailFieldErrorMsg.value = UiText.DynamicText("")
                true
            }
            else -> {
                isErrorEmailField.value = true
                emailFieldErrorMsg.value = UiText.ResourceText(emailResId)
                false
            }
        }
    }

    private fun validateFullName(): Boolean {
        return when (val fullNameResId: Int? =
            Validation.validateFullName(nameFieldText.value)
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
            Validation.validatePhoneNumber(phoneNumberFieldText.value)
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