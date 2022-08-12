package com.hero.ataa.data.local.repositories

import androidx.datastore.core.DataStore
import com.hero.ataa.domain.models.SearchHistory
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SearchHistoryRepository @Inject constructor(
    private val searchHistoryDataStore: DataStore<SearchHistory>
) {
    val historyFlow = searchHistoryDataStore.data
    suspend fun history() = searchHistoryDataStore.data.first()

    suspend fun update(transform: (SearchHistory) -> SearchHistory) {
        searchHistoryDataStore.updateData(transform)
    }
}