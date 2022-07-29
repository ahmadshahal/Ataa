package com.hero.ataa.ui.screens.language_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.data.local.repositories.SettingsRepository
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.system.RestartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val restartRepository: RestartRepository
) : ViewModel() {

    private val _uiEvent: Channel<UiEvent> = Channel()
    val uiEvent: Flow<UiEvent>
        get() = _uiEvent.receiveAsFlow()

    val isArabic = mutableStateOf(true)
    val loading = mutableStateOf(false)

    init {
        viewModelScope.launch {
            isArabic.value = settingsRepository.settings().arabic
        }
    }

    fun save() {
        viewModelScope.launch {
            if(settingsRepository.settings().arabic != isArabic.value) {
                loading.value = true
                settingsRepository.update {
                    it.copy(arabic = isArabic.value)
                }
                restartRepository.triggerRestart()
            }
            else {
                _uiEvent.send(UiEvent.PopBackStack)
            }
        }
    }
}