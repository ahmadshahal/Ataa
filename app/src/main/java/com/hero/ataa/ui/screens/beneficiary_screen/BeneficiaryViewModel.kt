package com.hero.ataa.ui.screens.beneficiary_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.shared.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class BeneficiaryViewModel @Inject constructor() : ViewModel() {

    private val _uiEvent: Channel<UiEvent> = Channel()
    val uiEvent: Flow<UiEvent>
        get() = _uiEvent.receiveAsFlow()

    private val _uiState = mutableStateOf<FormUiState>(FormUiState.Initial)
    val uiState: State<FormUiState>
        get() = _uiState

    val fullNameFieldText = mutableStateOf("")
    val isErrorNameField = mutableStateOf(false)
    val nameFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    val nationalNumberFieldText = mutableStateOf("")
    val isErrorNationalNumberField = mutableStateOf(false)
    val nationalNumberFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))
}