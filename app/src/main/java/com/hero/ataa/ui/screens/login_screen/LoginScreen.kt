package com.hero.ataa.ui.screens.login_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hero.ataa.R
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.ui.components.LinedTextField
import com.hero.ataa.ui.components.MaterialButton
import com.hero.ataa.ui.navigation.Screen

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
    isDarkMode: Boolean
) {
    val scrollState = rememberScrollState()
    val scaffoldState: ScaffoldState = rememberScaffoldState()
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
                    navController.navigate(uiEvent.route) {
                        popUpTo(route = Screen.LoginScreen.route) {
                            this.inclusive = true
                        }
                    }
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            TitleColumn(navController = navController, isDarkMode = isDarkMode)
            EmailTextField(viewModel = viewModel)
            Spacer(modifier = Modifier.height(5.dp))
            PasswordTextField(viewModel = viewModel)
            Spacer(modifier = Modifier.height(25.dp))
            MaterialButton(
                content = {
                    if (viewModel.uiState.value is LoginUiState.Initial) {
                        Text(
                            text = stringResource(id = R.string.login),
                            style = MaterialTheme.typography.button.copy(MaterialTheme.colors.onPrimary)
                        )
                    } else {
                        CircularProgressIndicator(
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.5.dp
                        )
                    }
                },
                onClick = { viewModel.onSubmit() },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                enabled = viewModel.uiState.value is LoginUiState.Initial
            )
            Spacer(modifier = Modifier.height(5.dp))
            SkipRow(navController = navController)
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

@Composable
private fun EmailTextField(viewModel: LoginViewModel) {
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
private fun PasswordTextField(
    viewModel: LoginViewModel,
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
            imeAction = ImeAction.Done
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
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        })
    )
}

@Composable
private fun SkipRow(navController: NavController) {
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
            onClick = {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(route = Screen.LoginScreen.route) {
                        this.inclusive = true
                    }
                }
            },
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
private fun TitleColumn(navController: NavController, isDarkMode: Boolean) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = if(isDarkMode) R.drawable.ic_dark_logo else R.drawable.ic_app_logo),
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
            )
            Spacer(modifier = Modifier.width(1.dp))
            TextButton(
                onClick = {
                    navController.navigate(Screen.RegisterScreen.route) {
                        popUpTo(route = Screen.LoginScreen.route) {
                            this.inclusive = true
                        }
                    }
                },
            ) {
                Text(
                    text = stringResource(id = R.string.do_create_new_account),
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.primary,
                    ),
                )
            }
        }
    }
}