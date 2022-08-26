package com.hero.ataa.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RectangularRadioButton(
    modifier: Modifier = Modifier,
    text: String,
    painter: Painter,
    isSelected: Boolean = false,
    iconWidth: Dp = 40.dp,
    iconHeight: Dp = 20.dp,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(75.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(
                if (isSelected) MaterialTheme.colors.primary
                else MaterialTheme.colors.surface
            )
            .border(
                width = if (isSelected) 0.0.dp else 0.1.dp,
                color = MaterialTheme.colors.secondaryVariant,
                shape = RoundedCornerShape(7.dp)
            )
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .height(iconHeight)
                    .width(iconWidth),
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                modifier = Modifier.weight(1F),
                text = text,
                style = MaterialTheme.typography.h5.copy(
                    color = if (isSelected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSurface
                )
            )
        }
    }
}