package com.hero.ataa.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Article
import androidx.compose.material.icons.rounded.Feed
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProgressRow(
    selectedItem: Int = 0,
) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        ProgressItem(
            imageVector = Icons.Rounded.Article,
            isSelected = selectedItem == 0,
            modifier = Modifier.weight(1F)
        )
        Spacer(modifier = Modifier.width(8.dp))
        ProgressItem(
            imageVector = Icons.Rounded.LocationOn,
            isSelected = selectedItem == 1,
            modifier = Modifier.weight(1F)
        )
        Spacer(modifier = Modifier.width(8.dp))
        ProgressItem(
            imageVector = Icons.Rounded.Feed,
            isSelected = selectedItem == 2,
            modifier = Modifier.weight(1F)
        )
    }
}