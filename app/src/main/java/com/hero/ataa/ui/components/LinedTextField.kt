package com.hero.ataa.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun LinedTextField(
    value: String,
    hint: String,
    onValueChanged: (String) -> Unit,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    errorMessage: String = "",
) {
    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(53.dp),
            value = value,
            onValueChange = onValueChanged,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                errorCursorColor = MaterialTheme.colors.primary,
                errorIndicatorColor = MaterialTheme.colors.error,
                errorLeadingIconColor = MaterialTheme.colors.primaryVariant,
                errorTrailingIconColor = MaterialTheme.colors.primaryVariant,
            ),
            isError = isError,
            textStyle = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onBackground),
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.primaryVariant)
                )
            },
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            singleLine = true,
        )
        if (isError) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.error)
            )
        }
    }
}