package com.hero.ataa.data.local.repositories

import androidx.datastore.core.DataStore
import com.hero.ataa.domain.models.Settings
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepository @Inject constructor(
    private val settingsDataStore: DataStore<Settings>
) {
    val settingsFlow = settingsDataStore.data

    suspend fun settings() = settingsDataStore.data.first()

    suspend fun update(transform: (Settings) -> Settings) {
        settingsDataStore.updateData(transform)
    }
}