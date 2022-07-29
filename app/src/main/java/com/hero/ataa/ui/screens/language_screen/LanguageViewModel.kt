package com.hero.ataa.ui.screens.language_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.data.local.repositories.SettingsRepository
import com.hero.ataa.shared.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    private val _uiEvent: Channel<UiEvent> = Channel()
    val uiEvent: Flow<UiEvent>
        get() = _uiEvent.receiveAsFlow()

    val isArabic = mutableStateOf<Boolean>(true)

    init {
        viewModelScope.launch {
            isArabic.value = settingsRepository.settings().arabic
        }
    }

    fun save() {
        viewModelScope.launch {
            settingsRepository.update {
                it.copy(arabic = isArabic.value)
            }
            _uiEvent.send(UiEvent.PopBackStack)
            // context.findActivity()?.recreate()
        }
    }
}