package com.hero.ataa.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import com.hero.ataa.data.local.serializers.UserSerializer
import com.hero.ataa.domain.models.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATA_STORE_FILE_NAME = "user.json"

@InstallIn(SingletonComponent::class)
@Module
object UserModule {
    @Singleton
    @Provides
    fun provideUserDataStore(@ApplicationContext appContext: Context): DataStore<User> {
        return DataStoreFactory.create(
            serializer = UserSerializer,
            produceFile = { appContext.dataStoreFile(DATA_STORE_FILE_NAME) },
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { User() }
            ),
        )
    }
}
