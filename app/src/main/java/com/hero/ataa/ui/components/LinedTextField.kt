package com.hero.ataa.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation


@Composable
fun LinedTextField(
    value: String,
    hint: String,
    onValueChanged: (String) -> Unit,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChanged,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        textStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onBackground),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primaryVariant)
            )
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        singleLine = true,
    )
}