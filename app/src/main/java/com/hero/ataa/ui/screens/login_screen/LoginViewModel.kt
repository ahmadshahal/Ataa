package com.hero.ataa.ui.screens.login_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    val emailTextState = mutableStateOf("")
    val passwordTextState = mutableStateOf("")

    val passwordVisibleState = mutableStateOf(false)

    val isErrorEmailField = mutableStateOf(false)
    val isErrorPasswordField = mutableStateOf(false)

    val emailFieldErrorMsg = mutableStateOf("")
    val passwordFieldErrorMsg = mutableStateOf("")

    private fun validateFields() : Boolean {
        var allGood = true
        if(emailTextState.value.isEmpty()) {
            allGood = false
            isErrorEmailField.value = true
            emailFieldErrorMsg.value = "حقل البريد الإلكتروني لا يمكن أن يكون فارغاً"
        }
        else {
            isErrorEmailField.value = false
            emailFieldErrorMsg.value = ""
        }
        if(passwordTextState.value.isEmpty()) {
            allGood = false
            isErrorPasswordField.value = true
            passwordFieldErrorMsg.value = " حقل كلمة المرور لا يمكن أن يكون فارغاً"
        }
        else {
            isErrorPasswordField.value = false
            passwordFieldErrorMsg.value = ""
        }
        return allGood
    }

    fun onSubmit() {
        validateFields()
    }
}