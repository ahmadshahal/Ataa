package com.hero.ataa.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hero.ataa.R

@Composable
fun ErrorWidget(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_error_pic),
            contentDescription = "",
            modifier = Modifier
                .height(250.dp)
                .width(200.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onClick,
            modifier = Modifier
                .height(45.dp)
                .clip(RoundedCornerShape(7.dp)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                disabledBackgroundColor = MaterialTheme.colors.primary,
                disabledContentColor = MaterialTheme.colors.onPrimary,
            ),
            shape = RoundedCornerShape(7.dp),
            elevation = ButtonDefaults.elevation(
                pressedElevation = 0.dp,
                disabledElevation = 0.dp,
                defaultElevation = 0.dp,
                focusedElevation = 0.dp,
                hoveredElevation = 0.dp
            ),
        ) {
            Text(
                text = stringResource(id = R.string.try_again),
                style = MaterialTheme.typography.button
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
    }
}