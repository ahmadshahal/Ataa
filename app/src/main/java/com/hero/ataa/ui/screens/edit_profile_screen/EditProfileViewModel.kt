package com.hero.ataa.ui.screens.edit_profile_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.R
import com.hero.ataa.domain.use_cases.EditProfileUseCase
import com.hero.ataa.shared.Constants
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.shared.UiText
import com.hero.ataa.utils.Validation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val editProfileUseCase: EditProfileUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = mutableStateOf<EditUiState>(EditUiState.Initial)
    val uiState: State<EditUiState>
        get() = _uiState

    private val _uiEvent: Channel<UiEvent> = Channel()
    val uiEvent: Flow<UiEvent>
        get() = _uiEvent.receiveAsFlow()

    val fullNameFieldText = mutableStateOf(savedStateHandle.get<String>(Constants.NavArgs.FULL_NAME_KEY)!!)
    val oldPasswordFieldText = mutableStateOf("")
    val passwordFieldText = mutableStateOf("")
    val confirmPasswordFieldText = mutableStateOf("")

    val oldPasswordVisible = mutableStateOf(false)
    val passwordVisible = mutableStateOf(false)
    val confirmPasswordVisible = mutableStateOf(false)

    val isErrorNameField = mutableStateOf(false)
    val isErrorPasswordField = mutableStateOf(false)
    val isErrorConfirmPasswordField = mutableStateOf(false)

    val nameFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))
    val passwordFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))
    val confirmPasswordFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))


    fun onSubmit() {
        val validateFullNameResult = validateFullName()
        val validatePasswordResult = validatePassword()
        val validateConfirmPasswordResult = validateConfirmPassword()
        if (validateFullNameResult
            && validatePasswordResult && validateConfirmPasswordResult
        ) {
            viewModelScope.launch {
                editProfileUseCase(
                    fullName = fullNameFieldText.value,
                    password = passwordFieldText.value,
                    oldPassword = oldPasswordFieldText.value
                ).collect { dataState ->
                    when (dataState) {
                        is DataState.Loading -> {
                            _uiState.value = EditUiState.Loading
                        }
                        is DataState.Error -> {
                            _uiState.value = EditUiState.Initial
                            _uiEvent.send(
                                UiEvent.ShowSnackBar(
                                    message = dataState.message,
                                )
                            )
                        }
                        is DataState.Success -> {
                            _uiState.value = EditUiState.Initial
                            _uiEvent.send(UiEvent.PopBackStack)
                        }
                        else -> Unit
                    }
                }
            }
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
}