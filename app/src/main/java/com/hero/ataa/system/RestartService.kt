package com.hero.ataa.system

import android.content.Context
import com.jakewharton.processphoenix.ProcessPhoenix
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RestartService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun triggerRestart() {
        ProcessPhoenix.triggerRebirth(context)
    }
}