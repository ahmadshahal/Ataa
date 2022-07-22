package com.hero.ataa.ui.screens.verification_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.R
import com.hero.ataa.domain.use_cases.ResendCodeUseCase
import com.hero.ataa.domain.use_cases.VerifyUseCase
import com.hero.ataa.shared.Constants
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.shared.UiText
import com.hero.ataa.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val verifyUseCase: VerifyUseCase,
    private val resendCodeUseCase: ResendCodeUseCase
) : ViewModel() {

    private val _verifyUiState = mutableStateOf<VerifyUiState>(VerifyUiState.Initial)
    val verifyUiState: State<VerifyUiState>
        get() = _verifyUiState

    private val _resendUiState = mutableStateOf<ResendUiState>(ResendUiState.Initial)
    val resendUiState: State<ResendUiState>
        get() = _resendUiState

    val firstFieldText = mutableStateOf("")
    val secondFieldText = mutableStateOf("")
    val thirdFieldText = mutableStateOf("")
    val fourthFieldText = mutableStateOf("")

    private val email = savedStateHandle.get<String>(Constants.NavArgs.EMAIL_KEY)!!

    private val _uiEvent: Channel<UiEvent> = Channel()
    val uiEvent: Flow<UiEvent>
        get() = _uiEvent.receiveAsFlow()

    fun onSubmit() {
        val language = Locale.getDefault().language
        val enteredCode =
            if (language == "ar")
                fourthFieldText.value + thirdFieldText.value + secondFieldText.value + firstFieldText.value
            else
                firstFieldText.value + secondFieldText.value + thirdFieldText.value + fourthFieldText.value
        if (enteredCode.length == 4) {
            viewModelScope.launch {
                verifyUseCase(
                    verifyCode = enteredCode,
                    email = email,
                ).collect { dataState ->
                    when (dataState) {
                        is DataState.Loading -> {
                            _verifyUiState.value = VerifyUiState.Loading
                        }
                        is DataState.Error -> {
                            _verifyUiState.value = VerifyUiState.Initial
                            _uiEvent.send(
                                UiEvent.ShowSnackBar(
                                    message = dataState.message,
                                )
                            )
                        }
                        is DataState.Success -> {
                            _verifyUiState.value = VerifyUiState.Initial
                            _uiEvent.send(UiEvent.Navigate(route = Screen.HomeScreen.route))
                        }
                        else -> Unit
                    }
                }
            }
        } else {
            viewModelScope.launch {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(
                        message = UiText.ResourceText(R.string.all_code_digits_must_be_filled)
                    )
                )
            }
        }
    }

    fun onResend() {
        viewModelScope.launch {
            resendCodeUseCase(
                email = email
            ).collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        _resendUiState.value = ResendUiState.Loading
                    }
                    is DataState.Error -> {
                        _resendUiState.value = ResendUiState.Initial
                        _uiEvent.send(
                            UiEvent.ShowSnackBar(
                                message = dataState.message,
                            )
                        )
                    }
                    is DataState.SuccessWithoutData -> {
                        _resendUiState.value = ResendUiState.Initial
                    }
                    else -> Unit
                }
            }
        }
    }
}