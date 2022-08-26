package com.hero.ataa.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun AppBar(
    title: @Composable () -> Unit = {},
    leading: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    elevation: Dp = 0.dp,
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = elevation,
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.align(Alignment.Center)) {
                title()
            }
            Box(modifier = Modifier.align(Alignment.CenterStart)) {
                leading()
            }
            Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    content = actions
                )
            }
        }
    }
}