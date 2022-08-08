package com.hero.ataa.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Spinner(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isError: Boolean = false,
    errorMessage: String = "",
    icon: ImageVector = Icons.Rounded.ArrowDropDown,
    iconSize: Dp = 24.dp,
    hint: String
) {
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(7.dp))
                .background(MaterialTheme.colors.surface)
                .border(
                    width = 0.1.dp,
                    color = MaterialTheme.colors.secondaryVariant,
                    shape = RoundedCornerShape(7.dp)
                )
                .clickable(onClick = onClick)
                .padding(start = 16.dp, end = 14.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1F)) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.primaryVariant),
                    )
                    if (text.isEmpty()) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterStart),
                            text = hint,
                            style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.primaryVariant)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = MaterialTheme.colors.onBackground,
                    modifier = Modifier.size(iconSize)
                )
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        if (isError) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.error)
            )
        }
    }

}