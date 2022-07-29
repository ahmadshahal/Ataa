package com.hero.ataa.data.local.repositories

import androidx.datastore.core.DataStore
import com.hero.ataa.domain.models.User
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDataStore: DataStore<User>
) {
    val userFlow = userDataStore.data

    suspend fun user() = userDataStore.data.first()

    suspend fun update(transform: (User) -> User) {
        userDataStore.updateData(transform)
    }
}