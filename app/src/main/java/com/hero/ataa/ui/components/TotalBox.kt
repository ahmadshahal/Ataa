package com.hero.ataa.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hero.ataa.R
import java.util.*

@Composable
fun TotalBox(modifier: Modifier = Modifier, total: String, currency: String) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(75.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(MaterialTheme.colors.surface)
            .border(
                width = 0.1.dp,
                color = MaterialTheme.colors.secondaryVariant,
                shape = RoundedCornerShape(7.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center).fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${stringResource(id = R.string.total)}: ",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground)
            )
            Text(
                text = "${
                    "%,d".format(
                        Locale.US,
                        if (total.isEmpty()) 0 else total.toInt()
                    )
                } $currency",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.primary)
            )
        }
    }
}