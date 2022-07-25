package com.hero.ataa.ui.screens.edit_name_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.domain.use_cases.EditProfileUseCase
import com.hero.ataa.shared.Constants
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.shared.UiText
import com.hero.ataa.ui.screens.edit_profile_screen.EditUiState
import com.hero.ataa.utils.Validation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val editProfileUseCase: EditProfileUseCase
) : ViewModel() {
    private val _uiState = mutableStateOf<EditUiState>(EditUiState.Initial)
    val uiState: State<EditUiState>
        get() = _uiState

    private val _uiEvent: Channel<UiEvent> = Channel()
    val uiEvent: Flow<UiEvent>
        get() = _uiEvent.receiveAsFlow()

    val fullNameFieldText = mutableStateOf(savedStateHandle.get<String>(Constants.NavArgs.FULL_NAME_KEY)!!)
    val isErrorNameField = mutableStateOf(false)
    val nameFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    fun onSubmit() {
        val validateFullNameResult = validateFullName()
        if (validateFullNameResult) {
            viewModelScope.launch {
                editProfileUseCase(
                    fullName = fullNameFieldText.value,
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
}