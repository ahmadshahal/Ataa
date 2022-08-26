package com.hero.ataa.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorPalette = lightColors(
    primary = Green600,
    onBackground = Black600,
    background = White300,
    secondary = Black600,
    secondaryVariant = Gray300,
    surface = White800,
    onPrimary = White800,
    onSecondary = White800,
    onSurface = Black600,
    primaryVariant = Gray600,
    error = Red800,
    onError = Gray200
)

private val DarkColorPalette = darkColors(
    primary = Green600,
    onBackground = White600,
    background = Black400,
    secondary = Black600,
    secondaryVariant = Color.Transparent,
    surface = Black600,
    onPrimary = White600,
    onSecondary = White600,
    onSurface = White600,
    primaryVariant = White500,
    error = Red800,
    onError = White500
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AtaaTheme(isDarkMode: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if(isDarkMode) DarkColorPalette else LightColorPalette

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if(isDarkMode) DarkColorPalette.background else LightColorPalette.background,
            darkIcons = !isDarkMode
        )
    }

    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null,
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}