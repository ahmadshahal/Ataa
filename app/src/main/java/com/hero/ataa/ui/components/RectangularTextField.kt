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
import androidx.compose.ui.unit.dp

@Composable
fun RectangularTextField(
    value: String,
    hint: String,
    onValueChanged: (String) -> Unit,
    trailingIcon: @Composable () -> Unit = {},
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
                .height(50.dp)
                .clip(RoundedCornerShape(7.dp))
                .background(MaterialTheme.colors.surface)
                .border(
                    width = 0.1.dp,
                    color = MaterialTheme.colors.secondaryVariant,
                    shape = RoundedCornerShape(7.dp)
                )
                .padding(start = 16.dp, end = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
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