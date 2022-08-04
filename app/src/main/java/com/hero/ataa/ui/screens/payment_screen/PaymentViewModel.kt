package com.hero.ataa.ui.screens.payment_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.data.local.repositories.SettingsRepository
import com.hero.ataa.domain.use_cases.PayUseCase
import com.hero.ataa.shared.Constants
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.system.notification.NotificationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val settingsRepository: SettingsRepository,
    private val notificationsRepository: NotificationsRepository,
    private val payUseCase: PayUseCase
) : ViewModel() {

    private val _uiEvent: Channel<UiEvent> = Channel()
    val uiEvent: Flow<UiEvent>
        get() = _uiEvent.receiveAsFlow()

    private val donationValue = savedStateHandle.get<String>(Constants.NavArgs.DONATION_VALUE_KEY)!!
    private val projectId = savedStateHandle.get<String>(Constants.NavArgs.PROJECT_ID_KEY)!!

    val chosenIdx = mutableStateOf(0)
    val loadingDialogState = mutableStateOf(false)

    fun onSubmit() {
        viewModelScope.launch {
            payUseCase(
                donationValue = donationValue,
                projectId = projectId
            ).collect { dataState ->
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
                    is DataState.Success -> {
                        loadingDialogState.value = false
                        if(settingsRepository.settings().notifications) {
                             notificationsRepository.triggerPaymentNotification()
                        }
                        _uiEvent.send(UiEvent.PopBackStack)
                        _uiEvent.send(UiEvent.SendUrlIntent(url = dataState.data))
                    }
                    else -> Unit
                }
            }
        }
    }
}