package com.hero.ataa.ui.screens.login_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hero.ataa.shared.UiText
import com.hero.ataa.utils.Validation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    val emailFieldText = mutableStateOf("")
    val passwordFieldText = mutableStateOf("")

    val passwordVisible = mutableStateOf(false)

    val isErrorEmailField = mutableStateOf(false)
    val isErrorPasswordField = mutableStateOf(false)

    val emailFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))
    val passwordFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    fun onSubmit() {
        val validateEmailResult = validateEmail()
        val validatePasswordResult = validatePassword()
        if (validateEmailResult && validatePasswordResult) {
            // TODO: GO
        }
    }

    private fun validateEmail(): Boolean {
        return when (val emailResId: Int? = Validation.validateEmail(emailFieldText.value)) {
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

    private fun validatePassword(): Boolean {
        return when (val passwordResId: Int? =
            Validation.validateLoginPassword(passwordFieldText.value)) {
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
}