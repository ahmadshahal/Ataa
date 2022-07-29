package com.hero.ataa

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.app.NotificationManagerCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.hero.ataa.notification.NotificationHelper
import com.hero.ataa.shared.Constants
import com.hero.ataa.ui.navigation.NavGraph
import com.hero.ataa.ui.theme.AtaaTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        NotificationHelper.createNotificationChannel(
            context = this,
            name = Constants.NOTIFICATION_CHANNEL_NAME,
            description = getString(R.string.ataa_notification_channel),
            importance = NotificationManagerCompat.IMPORTANCE_DEFAULT,
            showBadge = false,
        )

        NotificationHelper.createNotification(
            this,
            title = getString(R.string.donate_now),
            content = getString(R.string.donate_to_ataa_charity_project),
            channelName = Constants.NOTIFICATION_CHANNEL_NAME,
            bigText = getString(R.string.donate_to_ataa_charity_project),
        )

        // Preventing the System settings to change the font size.
        val configuration = resources.configuration
        configuration.fontScale = 1.0.toFloat()
        val metrics = resources.displayMetrics
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(metrics)
        metrics.scaledDensity = configuration.fontScale * metrics.density
        baseContext.resources.updateConfiguration(configuration, metrics)

        // Changing Locale.
        val config = resources.configuration
        val locale =
            if (mainViewModel.isArabic)
                Locale("ar")
            else
                Locale("en")
        Locale.setDefault(locale)
        config.setLocale(locale)
        createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)

        setContent {
            AtaaTheme(isDarkMode = mainViewModel.isDarkMode.value) {
                NavGraph(mainViewModel = mainViewModel)
            }
        }
    }
}