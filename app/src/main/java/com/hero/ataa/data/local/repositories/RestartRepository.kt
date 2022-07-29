package com.hero.ataa.data.local.repositories

import android.content.Context
import com.jakewharton.processphoenix.ProcessPhoenix
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RestartRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun triggerRestart() {
        ProcessPhoenix.triggerRebirth(context);
    }
}