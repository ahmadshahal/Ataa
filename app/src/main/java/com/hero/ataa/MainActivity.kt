package com.hero.ataa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hero.ataa.ui.screens.login_screen.LoginScreen
import com.hero.ataa.ui.theme.AtaaTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Forcing Arabic as main Locale.
        val config = resources.configuration
        val locale = Locale("ar")
        Locale.setDefault(locale)
        config.setLocale(locale)
        createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)

        setContent {
            AtaaTheme {
                LoginScreen()
            }
        }
    }
}