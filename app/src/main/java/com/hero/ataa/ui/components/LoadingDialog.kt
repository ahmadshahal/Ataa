package com.hero.ataa.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun LoadingDialog() {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        LoadingDots(
            circleColor = MaterialTheme.colors.primary,
            circleSize = 10.dp,
            spaceBetween = 8.dp,
            travelDistance = 7.dp
        )
    }
}