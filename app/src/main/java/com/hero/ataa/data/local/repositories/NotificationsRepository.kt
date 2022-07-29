package com.hero.ataa.data.local.repositories

import android.content.Context
import com.hero.ataa.R
import com.hero.ataa.notification.NotificationHelper
import com.hero.ataa.shared.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotificationsRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun triggerPaymentNotification() {
        NotificationHelper.createNotification(
            context = context,
            title = context.getString(R.string.thank_you),
            content = context.getString(R.string.thank_you_for_considering),
            channelName = Constants.NOTIFICATION_CHANNEL_NAME,
            bigText = context.getString(R.string.thank_you_for_considering),
        )
    }
}