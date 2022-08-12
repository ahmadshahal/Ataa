package com.hero.ataa

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import com.hero.ataa.shared.Constants
import com.hero.ataa.system.notification.NotificationHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AtaaApplication : Application() {
    /*
    override fun attachBaseContext(base: Context) {
        val config = Configuration()
        val locale = Locale("ar")
        config.setLocales(LocaleList(locale))
        super.attachBaseContext(base.createConfigurationContext(config))
    }
    */
    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createNotificationChannel(
            context = this,
            name = Constants.NOTIFICATION_CHANNEL_NAME,
            description = getString(R.string.ataa_notification_channel),
            importance = NotificationManagerCompat.IMPORTANCE_DEFAULT,
            showBadge = false,
        )
    }
}