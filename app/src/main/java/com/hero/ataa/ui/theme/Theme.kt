package com.hero.ataa.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = Green600,
    onBackground = Black600,
    background = White300,
    secondary = Black600,
    secondaryVariant = Gray300,
    surface = Gray600,
    onPrimary = White800,
    onSecondary = White800,
)

@Composable
fun AtaaTheme(content: @Composable () -> Unit) {
    val colors = LightColorPalette
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}