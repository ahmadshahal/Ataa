package com.hero.ataa.ui.screens.login_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hero.ataa.R
import com.hero.ataa.ui.components.LinedTextField
import com.hero.ataa.ui.components.MaterialButton

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val scrollState = rememberScrollState()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            WelcomeColumn()
            EmailTextField(viewModel = viewModel)
            Spacer(modifier = Modifier.height(5.dp))
            PasswordTextField(
                viewModel = viewModel,
            )
            Spacer(modifier = Modifier.height(25.dp))
            MaterialButton(
                text = stringResource(id = R.string.login),
                onClicked = { viewModel.onSubmit() },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
            )
            Spacer(modifier = Modifier.height(5.dp))
            SkipRow()
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

@Composable
fun EmailTextField(viewModel: LoginViewModel) {
    LinedTextField(
        value = viewModel.emailTextState.value,
        hint = stringResource(id = R.string.email),
        onValueChanged = { newValue ->
            viewModel.emailTextState.value = newValue
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_email_icon),
                contentDescription = "",
                modifier = Modifier.size(18.dp)
            )
        },
        isError = viewModel.isErrorEmailField.value,
        errorMessage = viewModel.emailFieldErrorMsg.value,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun PasswordTextField(
    viewModel: LoginViewModel,
) {
    LinedTextField(
        value = viewModel.passwordTextState.value,
        hint = stringResource(id = R.string.password),
        onValueChanged = { newValue ->
            viewModel.passwordTextState.value = newValue
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_password_icon),
                contentDescription = "",
                modifier = Modifier.size(19.dp)
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(
                onClick = {
                    viewModel.passwordVisibleState.value = !viewModel.passwordVisibleState.value
                },
            ) {
                Icon(
                    painter = painterResource(
                        id = if (viewModel.passwordVisibleState.value) R.drawable.ic_show_eye_icon else R.drawable.ic_hide_eye_icon
                    ),
                    contentDescription = "",
                    modifier = Modifier.size(19.dp),
                )
            }
        },
        isError = viewModel.isErrorPasswordField.value,
        errorMessage = viewModel.passwordFieldErrorMsg.value,
        visualTransformation = if (viewModel.passwordVisibleState.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun SkipRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.login_later),
            style = MaterialTheme.typography.subtitle1.copy(
                color = MaterialTheme.colors.onBackground,
            ),
        )
        Spacer(modifier = Modifier.width(1.dp))
        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.width(45.dp)
        ) {
            Text(
                text = stringResource(id = R.string.skip),
                style = MaterialTheme.typography.subtitle1.copy(
                    color = MaterialTheme.colors.primary,
                ),
            )
        }
    }
}

@Composable
fun WelcomeColumn() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_app_logo),
            contentDescription = "",
            modifier = Modifier
                .height(174.067.dp)
                .width(158.8.dp),
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.welcome_to_ataa),
            style = MaterialTheme.typography.h2.copy(color = MaterialTheme.colors.onBackground),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.welcome_on_board_again),
            style = MaterialTheme.typography.body2.copy(MaterialTheme.colors.onBackground),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.to_your_account),
                style = MaterialTheme.typography.body2.copy(MaterialTheme.colors.onBackground),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(1.dp))
            TextButton(
                onClick = { /*TODO*/ },
            ) {
                Text(
                    text = stringResource(id = R.string.create_new_account),
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.primary,
                    ),
                )
            }
        }
    }
}