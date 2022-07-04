package com.hero.ataa

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.hero.ataa.ui.navigation.NavGraph
import com.hero.ataa.ui.theme.AtaaTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Ataa)
        super.onCreate(savedInstanceState)
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