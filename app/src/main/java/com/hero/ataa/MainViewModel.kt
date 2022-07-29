package com.hero.ataa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.data.local.repositories.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val settingsRepository: SettingsRepository
) : ViewModel() {
    val darkModeFlow = settingsRepository.settingsFlow.map { it.darkMode }

    var loading: Boolean = true

    init {
        viewModelScope.launch {
            loading = false
        }
    }
}