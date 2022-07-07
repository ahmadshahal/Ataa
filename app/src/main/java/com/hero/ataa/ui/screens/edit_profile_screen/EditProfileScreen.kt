package com.hero.ataa.ui.screens.edit_profile_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hero.ataa.MainViewModel
import com.hero.ataa.R
import com.hero.ataa.ui.components.AppBar
import com.hero.ataa.ui.components.MaterialButton
import com.hero.ataa.ui.components.RectangularTextField

@Composable
fun EditProfileScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    viewModel: EditProfileViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            EditProfileAppBar(navController = navController)
        },
        contentColor = MaterialTheme.colors.onBackground,
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
                Spacer(modifier = Modifier.height(32.dp))
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(60.dp))
                        .border(
                            shape = RoundedCornerShape(60.dp),
                            color = MaterialTheme.colors.primary,
                            width = 2.dp
                        )
                        .background(MaterialTheme.colors.surface),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.AccountCircle,
                        contentDescription = "",
                        tint = MaterialTheme.colors.primaryVariant.copy(alpha = 0.2F),
                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.Center)
                    )
                }
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "ahmad.alshahal2@gmail.com",
                    style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(25.dp))
                FullNameTextField(viewModel = viewModel)
                Spacer(modifier = Modifier.height(16.dp))
                PasswordTextField(viewModel = viewModel)
                Spacer(modifier = Modifier.height(16.dp))
                ConfirmPasswordTextField(viewModel = viewModel)
                Spacer(modifier = Modifier.height(16.dp))
                PhoneNumberTextField(viewModel = viewModel)
                Spacer(modifier = Modifier.height(16.dp))
            }
            MaterialButton(
                modifier = Modifier.padding(vertical = 16.dp),
                content = {
                    Text(
                        text = stringResource(id = R.string.save),
                        style = MaterialTheme.typography.button.copy(color = MaterialTheme.colors.onPrimary)
                    )
                },
                onClick = {
                    // TODO.
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
            )
        }
    }
}

@Composable
private fun EditProfileAppBar(navController: NavController) {
    AppBar(
        title = {
            Text(
                text = stringResource(id = R.string.edit_profile),
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
        }
    )
}

@Composable
private fun FullNameTextField(viewModel: EditProfileViewModel) {
    val focusManager = LocalFocusManager.current
    RectangularTextField(
        value = viewModel.fullNameFieldText.value,
        hint = stringResource(id = R.string.full_name),
        onValueChanged = {
            viewModel.fullNameFieldText.value = it
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Next)
            }
        ),
        isError = viewModel.isErrorNameField.value,
        errorMessage = viewModel.nameFieldErrorMsg.value.asString()
    )
}

@Composable
private fun PasswordTextField(viewModel: EditProfileViewModel) {
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
private fun ConfirmPasswordTextField(viewModel: EditProfileViewModel) {
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
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Next)
            }
        ),
        isError = viewModel.isErrorConfirmPasswordField.value,
        errorMessage = viewModel.confirmPasswordFieldErrorMsg.value.asString(),
        visualTransformation = if (viewModel.confirmPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
    )
}

@Composable
private fun PhoneNumberTextField(viewModel: EditProfileViewModel) {
    val focusManager = LocalFocusManager.current
    RectangularTextField(
        value = viewModel.phoneNumberFieldText.value,
        hint = stringResource(id = R.string.phone_number),
        onValueChanged = {
            viewModel.phoneNumberFieldText.value = it
        },
        trailingIcon = {

        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        isError = viewModel.isErrorPhoneNumberField.value,
        errorMessage = viewModel.phoneNumberFieldErrorMsg.value.asString()
    )
}