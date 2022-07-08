package com.hero.ataa.ui.screens.profile_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.domain.use_cases.LogoutUseCase
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _uiEvent: Channel<UiEvent> = Channel()
    val uiEvent: Flow<UiEvent>
        get() = _uiEvent.receiveAsFlow()

    val loadingDialogState = mutableStateOf<Boolean>(false)

    fun logout() {
        viewModelScope.launch {
            logoutUseCase().collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        loadingDialogState.value = true
                    }
                    is DataState.Error -> {
                        loadingDialogState.value = false
                        _uiEvent.send(
                            UiEvent.ShowSnackBar(
                                message = dataState.message,
                            )
                        )
                    }
                    is DataState.SuccessWithoutData -> {
                        loadingDialogState.value = false
                        _uiEvent.send(
                            UiEvent.Navigate(route = Screen.LoginScreen.route)
                        )
                    }
                    else -> Unit
                }
            }
        }
    }
}