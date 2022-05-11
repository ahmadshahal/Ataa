package com.hero.ataa.ui.screens.login_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hero.ataa.R
import com.hero.ataa.ui.components.LinedTextField
import com.hero.ataa.ui.components.MaterialButton

@Composable
fun LoginScreen() {
    val emailText = remember {
        mutableStateOf("")
    }
    val passwordText = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember {
        mutableStateOf(false)
    }
    Scaffold(backgroundColor = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_app_logo),
                contentDescription = "",
                modifier = Modifier
                    .height(174.067.dp)
                    .width(158.8.dp),
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                modifier = Modifier.fillMaxWidth(0.8F),
                text = stringResource(id = R.string.welcome_to_ataa),
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier.fillMaxWidth(0.8F),
                text = stringResource(id = R.string.welcome_on_board_again),
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            LinedTextField(
                value = emailText.value,
                hint = stringResource(id = R.string.email),
                onValueChanged = { newValue ->
                    emailText.value = newValue
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email_icon),
                        contentDescription = "",
                        modifier = Modifier.size(18.dp)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(5.dp))
            LinedTextField(
                value = passwordText.value,
                hint = stringResource(id = R.string.password),
                onValueChanged = { newValue ->
                    passwordText.value = newValue
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
                    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                        Icon(
                            painter = painterResource(id = if (passwordVisible.value) R.drawable.ic_show_eye_icon else R.drawable.ic_hide_eye_icon),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(25.dp))
            MaterialButton(
                text = stringResource(id = R.string.login),
                onClicked = { /*TODO*/ },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
            )
        }
    }
}