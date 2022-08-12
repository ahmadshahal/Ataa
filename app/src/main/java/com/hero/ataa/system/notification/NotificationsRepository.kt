package com.hero.ataa.system.notification

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import com.hero.ataa.R
import com.hero.ataa.shared.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotificationsRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun triggerPaymentNotification() {
        NotificationHelper.createNotificationChannel(
            context = context,
            name = Constants.NOTIFICATION_CHANNEL_NAME,
            description = context.getString(R.string.ataa_notification_channel),
            importance = NotificationManagerCompat.IMPORTANCE_DEFAULT,
            showBadge = false,
        )
        NotificationHelper.createNotification(
            context,
            title = context.getString(R.string.donate_now),
            content = context.getString(R.string.donate_to_ataa_charity_project),
            channelName = Constants.NOTIFICATION_CHANNEL_NAME,
            bigText = context.getString(R.string.donate_to_ataa_charity_project),
        )
    }
}