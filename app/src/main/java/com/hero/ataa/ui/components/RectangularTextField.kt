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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RectangularTextField(
    value: String,
    hint: String,
    height: Dp = 50.dp,
    onValueChanged: (String) -> Unit,
    trailingIcon: @Composable () -> Unit = {},
    prefixIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    errorMessage: String = "",
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .clip(RoundedCornerShape(7.dp))
                .background(MaterialTheme.colors.surface)
                .border(
                    width = 0.1.dp,
                    color = MaterialTheme.colors.secondaryVariant,
                    shape = RoundedCornerShape(7.dp)
                )
                .padding(start = 16.dp, end = 14.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (prefixIcon != null) {
                    prefixIcon()
                    Spacer(modifier = Modifier.width(10.dp))
                }
                Box(modifier = Modifier.weight(1F)) {
                    BasicTextField(
                        value = value,
                        onValueChange = onValueChanged,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterStart),
                        textStyle = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onBackground),
                        visualTransformation = visualTransformation,
                        keyboardOptions = keyboardOptions,
                        singleLine = true,
                        keyboardActions = keyboardActions,
                        cursorBrush = SolidColor(value = MaterialTheme.colors.primary)
                    )
                    if (value.isEmpty()) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterStart),
                            text = hint,
                            style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.primaryVariant)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(5.dp))
                trailingIcon()
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


@Composable
fun LargeRectangularTextField(
    value: String,
    hint: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    errorMessage: String = "",
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 120.dp)
                .clip(RoundedCornerShape(7.dp))
                .background(MaterialTheme.colors.surface)
                .border(
                    width = 0.1.dp,
                    color = MaterialTheme.colors.secondaryVariant,
                    shape = RoundedCornerShape(7.dp)
                )
                .padding(16.dp)
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChanged,
                modifier = Modifier
                    .fillMaxSize(),
                textStyle = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onBackground),
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                cursorBrush = SolidColor(value = MaterialTheme.colors.primary),
                maxLines = 15
            )
            if (value.isEmpty()) {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.primaryVariant)
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
