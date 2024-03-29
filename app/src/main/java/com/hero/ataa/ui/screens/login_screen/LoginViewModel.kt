package com.hero.ataa.ui.screens.login_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.domain.use_cases.LoginUseCase
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.shared.UiText
import com.hero.ataa.ui.navigation.Screen
import com.hero.ataa.utils.Validation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val _uiState = mutableStateOf<LoginUiState>(LoginUiState.Initial)
    val uiState: State<LoginUiState>
        get() = _uiState

    private val _uiEvent: Channel<UiEvent> = Channel()
    val uiEvent: Flow<UiEvent>
        get() = _uiEvent.receiveAsFlow()

    val emailFieldText = mutableStateOf("")
    val passwordFieldText = mutableStateOf("")

    val passwordVisible = mutableStateOf(false)

    val isErrorEmailField = mutableStateOf(false)
    val isErrorPasswordField = mutableStateOf(false)

    val emailFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))
    val passwordFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    fun onDestroy() {
        viewModelScope.cancel()
    }

    fun onSubmit() {
        val validateEmailResult = validateEmail()
        val validatePasswordResult = validatePassword()
        if (validateEmailResult && validatePasswordResult) {
            viewModelScope.launch {
                loginUseCase(
                    email = emailFieldText.value,
                    password = passwordFieldText.value
                ).collect { dataState ->
                    when (dataState) {
                        is DataState.Loading -> {
                            _uiState.value = LoginUiState.Loading
                        }
                        is DataState.Error -> {
                            _uiState.value = LoginUiState.Initial
                            _uiEvent.send(
                                UiEvent.ShowSnackBar(
                                    message = dataState.message,
                                )
                            )
                        }
                        is DataState.SuccessWithoutData -> {
                            _uiState.value = LoginUiState.Initial
                            _uiEvent.send(UiEvent.Navigate(route = Screen.HomeScreen.route))
                        }
                        else -> Unit
                    }
                }
            }
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