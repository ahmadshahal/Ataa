package com.hero.ataa.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ProgressItem(
    imageVector: ImageVector,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onError)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier.size(22.dp)
        )
    }
}