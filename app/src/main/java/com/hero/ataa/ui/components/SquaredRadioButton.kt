package com.hero.ataa.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SquaredRadioButton(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(85.dp)
            .width(95.dp)
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
            .padding(15.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            style = MaterialTheme.typography.h5.copy(
                color = if (isSelected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSurface
            ),
            textAlign = TextAlign.Center,
            lineHeight = 18.sp
        )
    }
}