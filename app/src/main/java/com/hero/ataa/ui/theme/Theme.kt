package com.hero.ataa.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

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
    error = Red800
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AtaaTheme(content: @Composable () -> Unit) {
    val colors = LightColorPalette
    CompositionLocalProvider(
        LocalOverScrollConfiguration provides null,
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}