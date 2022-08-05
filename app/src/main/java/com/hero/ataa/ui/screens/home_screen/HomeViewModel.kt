package com.hero.ataa.ui.screens.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.data.local.repositories.UserRepository
import com.hero.ataa.domain.use_cases.GetAdsUseCase
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
class HomeViewModel @Inject constructor(
    private val getAdsUseCase: GetAdsUseCase,
    private val logoutUseCase: LogoutUseCase,
    userRepository: UserRepository,
) : ViewModel() {

    private val _adsUiState = mutableStateOf<AdsUiState>(AdsUiState.Loading)
    val adsUiState: State<AdsUiState>
        get() = _adsUiState

    val userLoggedInFlow = userRepository.loggedInFlow

    private val _uiEvent: Channel<UiEvent> = Channel()
    val uiEvent: Flow<UiEvent>
        get() = _uiEvent.receiveAsFlow()

    val loggedInFlow = userRepository.loggedInFlow

    val logOutPopUpDialogState = mutableStateOf<Boolean>(false)
    val loadingDialogState = mutableStateOf<Boolean>(false)

    init {
        getAds()
    }

    fun getAds() {
        viewModelScope.launch {
            getAdsUseCase().collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        _adsUiState.value = AdsUiState.Loading
                    }
                    is DataState.Error -> {
                        _adsUiState.value = AdsUiState.Error
                        _uiEvent.send(
                            UiEvent.ShowSnackBar(
                                message = dataState.message,
                            )
                        )
                    }
                    is DataState.Success -> {
                        _adsUiState.value = AdsUiState.Success(adsList = dataState.data)
                    }
                    else -> Unit
                }
            }
        }
    }

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