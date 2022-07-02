package com.hero.ataa.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun TitledTextField(
    title: String,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(MaterialTheme.colors.surface)
            .border(
                width = 0.1.dp,
                color = MaterialTheme.colors.secondaryVariant,
                shape = RoundedCornerShape(7.dp)
            )
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onBackground),
            )
            Spacer(modifier = Modifier.height(5.dp))
            BasicTextField(
                value = value,
                onValueChange = onValueChanged,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = MaterialTheme.typography.subtitle2.copy(color = MaterialTheme.colors.primary),
                keyboardOptions = keyboardOptions,
                visualTransformation = visualTransformation,
                keyboardActions = keyboardActions,
                cursorBrush = SolidColor(value = MaterialTheme.colors.primary),
            )
        }
    }
}