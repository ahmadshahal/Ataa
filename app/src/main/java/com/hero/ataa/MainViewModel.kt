package com.hero.ataa

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.data.local.repositories.AlarmsRepository
import com.hero.ataa.data.local.repositories.SettingsRepository
import com.hero.ataa.data.local.repositories.UserRepository
import com.hero.ataa.domain.use_cases.ValidateTokenUseCase
import com.hero.ataa.shared.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val settingsRepository: SettingsRepository,
    validateTokenUseCase: ValidateTokenUseCase,
    val userRepository: UserRepository,
    alarmsRepository: AlarmsRepository
) : ViewModel() {
    val darkModeFlow = settingsRepository.settingsFlow.map { it.darkMode }

    var loading: Boolean = true

    val loggedIn = mutableStateOf(false)

    init {
        viewModelScope.launch {
            if(userRepository.user().firstLaunch) {
                alarmsRepository.setUpAlarm()
                userRepository.update {
                    it.copy(firstLaunch = false)
                }
            }
            validateTokenUseCase.invoke().collect { dataState ->
                when (dataState) {
                    !is DataState.Loading -> {
                        loggedIn.value = userRepository.loggedInFlow.first()
                        delay(500)
                        loading = false
                    }
                    else -> Unit
                }
            }
        }
    }
}