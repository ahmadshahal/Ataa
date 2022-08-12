package com.hero.ataa.system.alarms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hero.ataa.R
import com.hero.ataa.data.local.repositories.NOTIFICATION_ALARM_ACTION
import com.hero.ataa.data.local.repositories.SettingsRepository
import com.hero.ataa.shared.Constants
import com.hero.ataa.system.notification.NotificationHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    override fun onReceive(context: Context?, intent: Intent?) = runBlocking {
        if(context != null && intent != null) {
            if(intent.action == NOTIFICATION_ALARM_ACTION && settingsRepository.settings().notifications) {
                NotificationHelper.createNotification(
                    context,
                    title = context.getString(R.string.donate_now),
                    content = context.getString(R.string.donate_to_ataa_charity_project),
                    channelName = Constants.NOTIFICATION_CHANNEL_NAME,
                    bigText = context.getString(R.string.donate_to_ataa_charity_project),
                )
            }
        }
    }
}