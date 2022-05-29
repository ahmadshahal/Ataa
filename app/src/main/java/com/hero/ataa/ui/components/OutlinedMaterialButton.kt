package com.hero.ataa.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OutlinedMaterialButton(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    onClicked: () -> Unit,
    backgroundColor: Color,
    enabled: Boolean = true,
) {
    OutlinedButton(
        onClick = onClicked,
        modifier = modifier
            .fillMaxWidth()
            .height(45.dp),
        enabled = enabled,
        border = BorderStroke(color = backgroundColor, width = 1.dp),
        shape = RoundedCornerShape(7.dp)
    ) {
        content()
    }
}