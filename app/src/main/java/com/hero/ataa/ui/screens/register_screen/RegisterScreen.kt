package com.hero.ataa.ui.screens.register_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hero.ataa.R
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.ui.components.LinedTextField
import com.hero.ataa.ui.components.MaterialButton
import com.hero.ataa.ui.navigation.Screen
import com.hero.ataa.utils.Country

@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel = hiltViewModel()) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = uiEvent.message.asString(context),
                    )
                }
                is UiEvent.Navigate -> {
                    // TODO.
                }
                else -> Unit
            }
        }
    }

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
        if (viewModel.showDialog.value) {
            SelectCountryDialog(viewModel = viewModel)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            TitleColumn(navController = navController)
            Spacer(modifier = Modifier.height(5.dp))
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
                    if (viewModel.uiState.value is RegisterUiState.Initial) {
                        Text(
                            text = stringResource(id = R.string.next),
                            style = MaterialTheme.typography.button.copy(MaterialTheme.colors.onPrimary)
                        )
                    } else {
                        CircularProgressIndicator(
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.5.dp,
                        )
                    }
                },
                onClicked = { viewModel.onSubmit() },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                enabled = viewModel.uiState.value is RegisterUiState.Initial
            )
            Spacer(modifier = Modifier.height(5.dp))
            SkipRow()
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

@Composable
private fun TitleColumn(navController: NavController) {
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
            onClick = {
                navController.navigate(Screen.LoginScreen.route) {
                    popUpTo(route = Screen.RegisterScreen.route) {
                        this.inclusive = true
                    }
                }
            },
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
    val focusManager = LocalFocusManager.current
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
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        })
    )
}

@Composable
private fun NameTextField(viewModel: RegisterViewModel) {
    val focusManager = LocalFocusManager.current
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
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        })
    )
}

@Composable
private fun PasswordTextField(
    viewModel: RegisterViewModel,
) {
    val focusManager = LocalFocusManager.current
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
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
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
        visualTransformation = if (viewModel.passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        })
    )
}

@Composable
private fun ConfirmPasswordTextField(
    viewModel: RegisterViewModel,
) {
    val focusManager = LocalFocusManager.current
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
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
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
        visualTransformation = if (viewModel.confirmPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        })
    )
}

@Composable
private fun PhoneNumberTextField(viewModel: RegisterViewModel) {
    val focusManager = LocalFocusManager.current
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
        trailingIcon = {
            IconButton(onClick = { viewModel.showDialog.value = true }) {
                Text(
                    text = Country.getFlagEmojiFor(viewModel.chosenCountry.value.nameCode),
                    style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onBackground)
                )
            }
        },
        isError = viewModel.isErrorPhoneNumberField.value,
        errorMessage = viewModel.phoneNumberFieldErrorMsg.value.asString(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        })
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

@Composable
private fun SelectCountryDialog(viewModel: RegisterViewModel) {
    Dialog(
        onDismissRequest = {
            viewModel.showDialog.value = false
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(0.9F)
                .clip(RoundedCornerShape(7.dp))
                .background(MaterialTheme.colors.background)
        ) {
            LazyColumn(
                modifier = Modifier,
            ) {
                items(Country.getCountriesList()) { country ->
                    CountryItem(viewModel = viewModel, country = country)
                }
            }
        }
    }
}

@Composable
private fun CountryItem(viewModel: RegisterViewModel, country: Country) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                viewModel.chosenCountry.value = country
                viewModel.showDialog.value = false
            }
            .padding(horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = Country.getFlagEmojiFor(countryCode = country.nameCode),
            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onBackground),
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            modifier = Modifier.weight(1F),
            text = country.fullName,
            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onBackground),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

}
