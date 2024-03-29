package com.hero.ataa

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.hero.ataa.ui.navigation.NavGraph
import com.hero.ataa.ui.navigation.Screen
import com.hero.ataa.ui.theme.AtaaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import java.util.*


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.loading
            }
        }

        super.onCreate(savedInstanceState)

        setContent {
            AtaaTheme(isDarkMode = mainViewModel.darkModeFlow.collectAsState(initial = false).value) {
                val navController = rememberNavController()
                NavGraph(
                    startDestination = Screen.LoginScreen.route,
                    navController = navController
                )
                LaunchedEffect(key1 = Unit) {
                    mainViewModel.loggedIn.collect { loggedIn ->
                        if (loggedIn) {
                            navController.navigate(Screen.HomeScreen.route) {
                                popUpTo(Screen.LoginScreen.route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            }
        }

        val isArabic = runBlocking {
            mainViewModel.settingsRepository.settings().arabic
        }

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
            if (isArabic)
                Locale("ar")
            else
                Locale("en")
        Locale.setDefault(locale)
        config.setLocale(locale)
        createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}