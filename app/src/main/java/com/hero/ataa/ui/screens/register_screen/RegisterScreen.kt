package com.hero.ataa.ui.screens.register_screen

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
import androidx.compose.ui.text.input.KeyboardCapitalization
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
fun RegisterScreen(viewModel: RegisterViewModel = hiltViewModel()) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background,
        snackbarHost = { state ->
            SnackbarHost(state) { data ->
                Snackbar(
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = MaterialTheme.colors.onSecondary,
                    snackbarData = data,
                )
            }
        },
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
            TitleColumn()
            EmailTextField(viewModel = viewModel)
            Spacer(modifier = Modifier.height(5.dp))
            NameTextField(viewModel = viewModel)
            Spacer(modifier = Modifier.height(5.dp))
            PasswordTextField(viewModel = viewModel)
            Spacer(modifier = Modifier.height(5.dp))
            ConfirmPasswordTextField(viewModel = viewModel)
            Spacer(modifier = Modifier.height(5.dp))
            PhoneNumberTextField(viewModel = viewModel)
            Spacer(modifier = Modifier.height(25.dp))
            MaterialButton(
                content = {
                    Text(
                        text = stringResource(id = R.string.next),
                        style = MaterialTheme.typography.button.copy(MaterialTheme.colors.onPrimary)
                    )
                },
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
private fun TitleColumn() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.create_new_account),
        style = MaterialTheme.typography.h2.copy(color = MaterialTheme.colors.onBackground),
        textAlign = TextAlign.Center
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.already_have_an_account),
            style = MaterialTheme.typography.body2.copy(MaterialTheme.colors.onBackground),
        )
        Spacer(modifier = Modifier.width(1.dp))
        TextButton(
            onClick = { /*TODO*/ },
        ) {
            Text(
                text = stringResource(id = R.string.do_log_in),
                style = MaterialTheme.typography.body2.copy(
                    color = MaterialTheme.colors.primary,
                ),
            )
        }
    }
}

@Composable
private fun EmailTextField(viewModel: RegisterViewModel) {
    LinedTextField(
        value = viewModel.emailFieldText.value,
        hint = stringResource(id = R.string.email),
        onValueChanged = { newValue ->
            viewModel.emailFieldText.value = newValue
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_email_icon),
                contentDescription = "",
                modifier = Modifier.size(18.dp)
            )
        },
        isError = viewModel.isErrorEmailField.value,
        errorMessage = viewModel.emailFieldErrorMsg.value.asString(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
private fun NameTextField(viewModel: RegisterViewModel) {
    LinedTextField(
        value = viewModel.nameFieldText.value,
        hint = stringResource(id = R.string.full_name),
        onValueChanged = { newValue ->
            viewModel.nameFieldText.value = newValue
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_profile_icon),
                contentDescription = "",
                modifier = Modifier.size(21.dp)
            )
        },
        isError = viewModel.isErrorNameField.value,
        errorMessage = viewModel.nameFieldErrorMsg.value.asString(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Words
        )
    )
}

@Composable
private fun PasswordTextField(
    viewModel: RegisterViewModel,
) {
    LinedTextField(
        value = viewModel.passwordFieldText.value,
        hint = stringResource(id = R.string.password),
        onValueChanged = { newValue ->
            viewModel.passwordFieldText.value = newValue
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
                    viewModel.passwordVisible.value = !viewModel.passwordVisible.value
                },
            ) {
                Icon(
                    painter = painterResource(
                        id = if (viewModel.passwordVisible.value) R.drawable.ic_show_eye_icon else R.drawable.ic_hide_eye_icon
                    ),
                    contentDescription = "",
                    modifier = Modifier.size(19.dp),
                )
            }
        },
        isError = viewModel.isErrorPasswordField.value,
        errorMessage = viewModel.passwordFieldErrorMsg.value.asString(),
        visualTransformation = if (viewModel.passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
private fun ConfirmPasswordTextField(
    viewModel: RegisterViewModel,
) {
    LinedTextField(
        value = viewModel.confirmPasswordFieldText.value,
        hint = stringResource(id = R.string.confirm_password),
        onValueChanged = { newValue ->
            viewModel.confirmPasswordFieldText.value = newValue
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
                    viewModel.confirmPasswordVisible.value = !viewModel.confirmPasswordVisible.value
                },
            ) {
                Icon(
                    painter = painterResource(
                        id = if (viewModel.confirmPasswordVisible.value) R.drawable.ic_show_eye_icon else R.drawable.ic_hide_eye_icon
                    ),
                    contentDescription = "",
                    modifier = Modifier.size(19.dp),
                )
            }
        },
        isError = viewModel.isErrorConfirmPasswordField.value,
        errorMessage = viewModel.confirmPasswordFieldErrorMsg.value.asString(),
        visualTransformation = if (viewModel.confirmPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
private fun PhoneNumberTextField(viewModel: RegisterViewModel) {
    LinedTextField(
        value = viewModel.phoneNumberFieldText.value,
        hint = stringResource(id = R.string.phone_number),
        onValueChanged = { newValue ->
            viewModel.phoneNumberFieldText.value = newValue
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_phone_icon),
                contentDescription = "",
                modifier = Modifier.size(20.5.dp)
            )
        },
        isError = viewModel.isErrorPhoneNumberField.value,
        errorMessage = viewModel.phoneNumberFieldErrorMsg.value.asString(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
        )
    )
}

@Composable
private fun SkipRow() {
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
