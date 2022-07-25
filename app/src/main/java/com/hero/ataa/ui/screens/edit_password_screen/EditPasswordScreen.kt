package com.hero.ataa.ui.screens.edit_password_screen

import androidx.compose.foundation.ScrollState
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
import com.hero.ataa.ui.components.AppBar
import com.hero.ataa.ui.components.MaterialButton
import com.hero.ataa.ui.components.RectangularTextField
import com.hero.ataa.ui.screens.edit_profile_screen.EditUiState

@Composable
fun EditPasswordScreen(
    navController: NavController,
    viewModel: EditPasswordViewModel = hiltViewModel(),
) {
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
                    navController.navigate(uiEvent.route)
                }
                is UiEvent.PopBackStack -> {
                    navController.popBackStack()
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            EditPasswordAppBar(navController = navController, scrollState = scrollState)
        },
        contentColor = MaterialTheme.colors.onBackground,
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
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                OldPasswordTextField(viewModel = viewModel)
                Spacer(modifier = Modifier.height(16.dp))
                PasswordTextField(viewModel = viewModel)
                Spacer(modifier = Modifier.height(16.dp))
                ConfirmPasswordTextField(viewModel = viewModel)
                Spacer(modifier = Modifier.height(16.dp))
            }
            MaterialButton(
                modifier = Modifier.padding(vertical = 16.dp),
                content = {
                    if (viewModel.uiState.value is EditUiState.Initial) {
                        Text(
                            text = stringResource(id = R.string.save),
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
                onClick = { viewModel.onSubmit() },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                enabled = viewModel.uiState.value is EditUiState.Initial
            )
        }
    }
}

@Composable
private fun EditPasswordAppBar(navController: NavController, scrollState: ScrollState) {
    AppBar(
        title = {
            Text(
                text = stringResource(id = R.string.edit_password),
                style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground),
                textAlign = TextAlign.Center,
            )
        },
        leading = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_icon),
                    contentDescription = "",
                    modifier = Modifier.size(19.dp),
                    tint = MaterialTheme.colors.onBackground
                )
            }
        },
        elevation = if(scrollState.value > 0) 1.dp else 0.dp
    )
}

@Composable
private fun OldPasswordTextField(viewModel: EditPasswordViewModel) {
    val focusManager = LocalFocusManager.current
    RectangularTextField(
        value = viewModel.oldPasswordFieldText.value,
        hint = stringResource(id = R.string.old_password),
        onValueChanged = {
            viewModel.oldPasswordFieldText.value = it
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    viewModel.oldPasswordVisible.value = !viewModel.oldPasswordVisible.value
                },
            ) {
                Icon(
                    painter = painterResource(
                        id = if (viewModel.oldPasswordVisible.value) R.drawable.ic_show_eye_icon else R.drawable.ic_hide_eye_icon
                    ),
                    contentDescription = "",
                    modifier = Modifier.size(19.dp),
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Next)
            }
        ),
        isError = viewModel.isErrorOldPasswordField.value,
        errorMessage = viewModel.oldPasswordFieldErrorMsg.value.asString(),
        visualTransformation = if (viewModel.oldPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
    )
}

@Composable
private fun PasswordTextField(viewModel: EditPasswordViewModel) {
    val focusManager = LocalFocusManager.current
    RectangularTextField(
        value = viewModel.passwordFieldText.value,
        hint = stringResource(id = R.string.password),
        onValueChanged = {
            viewModel.passwordFieldText.value = it
        },
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
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Next)
            }
        ),
        isError = viewModel.isErrorPasswordField.value,
        errorMessage = viewModel.passwordFieldErrorMsg.value.asString(),
        visualTransformation = if (viewModel.passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
    )
}

@Composable
private fun ConfirmPasswordTextField(viewModel: EditPasswordViewModel) {
    val focusManager = LocalFocusManager.current
    RectangularTextField(
        value = viewModel.confirmPasswordFieldText.value,
        hint = stringResource(id = R.string.confirm_password),
        onValueChanged = {
            viewModel.confirmPasswordFieldText.value = it
        },
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
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        isError = viewModel.isErrorConfirmPasswordField.value,
        errorMessage = viewModel.confirmPasswordFieldErrorMsg.value.asString(),
        visualTransformation = if (viewModel.confirmPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
    )
}