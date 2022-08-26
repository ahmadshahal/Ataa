package com.hero.ataa.ui.screens.settings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.data.local.repositories.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    val darkModeFlow = settingsRepository.settingsFlow.map { it.darkMode }
    val notificationsFlow = settingsRepository.settingsFlow.map { it.notifications }

    fun updateDarkMode(on: Boolean) {
        viewModelScope.launch {
            settingsRepository.update { it.copy(darkMode = on) }
        }
    }

    fun updateNotifications(on: Boolean) {
        viewModelScope.launch {
            settingsRepository.update { it.copy(notifications = on) }
        }
    }
}