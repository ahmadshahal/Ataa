package com.hero.ataa.ui.screens.verification_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hero.ataa.R
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.ui.components.AppBar
import com.hero.ataa.ui.components.MaterialButton
import com.hero.ataa.ui.components.OneCharTextField
import com.hero.ataa.ui.navigation.Screen
import com.hero.ataa.ui.theme.AtaaFont
import java.util.*

@Composable
fun VerificationScreen(
    navController: NavController,
    email: String,
    viewModel: VerificationViewModel = hiltViewModel()
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
                        popUpTo(route = Screen.RegisterScreen.route) {
                            this.inclusive = true
                        }
                    }
                }
                else -> Unit
            }
        }
    }
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            VerificationAppBar(navController = navController)
        },
        scaffoldState = scaffoldState,
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
            verticalArrangement = Arrangement.Center,
        ) {
            ContentColumn(email = email, viewModel = viewModel)
            Spacer(modifier = Modifier.height(30.dp))
            BottomColumn(viewModel = viewModel)
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
private fun VerificationAppBar(navController: NavController) {
    AppBar(
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
private fun BottomColumn(viewModel: VerificationViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        MaterialButton(
            content = {
                if (viewModel.resendUiState.value is ResendUiState.Initial) {
                    Text(
                        text = stringResource(id = R.string.resend_code),
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
            onClick = { viewModel.onResend() },
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.onSecondary,
            enabled = viewModel.resendUiState.value is ResendUiState.Initial
        )
        Spacer(modifier = Modifier.height(15.dp))
        MaterialButton(
            content = {
                if (viewModel.verifyUiState.value is VerifyUiState.Initial) {
                    Text(
                        text = stringResource(id = R.string.next),
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
            enabled = viewModel.verifyUiState.value is VerifyUiState.Initial
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun ContentColumn(email: String, viewModel: VerificationViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_mobile_image),
            contentDescription = "",
            modifier = Modifier
                .height(161.dp)
                .width(80.78.dp),
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.account_verification),
            style = MaterialTheme.typography.h2.copy(color = MaterialTheme.colors.onBackground),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontSize = MaterialTheme.typography.body2.fontSize,
                        fontWeight = FontWeight.Normal,
                        fontFamily = AtaaFont
                    )
                ) {
                    append(stringResource(id = R.string.a_code_has_been_sent))
                }
                append(" ")
                withStyle(
                    SpanStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontSize = MaterialTheme.typography.body2.fontSize,
                        fontWeight = FontWeight.Bold,
                        fontFamily = AtaaFont
                    )
                ) {
                    append(email)
                }
            },
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        FieldsRow(viewModel = viewModel)
    }
}

@Composable
private fun FieldsRow(viewModel: VerificationViewModel) {
    val focusManager = LocalFocusManager.current
    val language = Locale.getDefault().language
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        OneCharTextField(
            value = viewModel.firstFieldText.value,
            onValueChanged = {
                if(it.isEmpty()) {
                    if(language == "ar") {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                    else
                        focusManager.clearFocus()
                    viewModel.firstFieldText.value = it
                }
                else if (it.length == 1) {
                    if(language == "ar") {
                        focusManager.clearFocus()
                    }
                    else
                        focusManager.moveFocus(FocusDirection.Next)
                    viewModel.firstFieldText.value = it
                }
            },
        )
        Spacer(modifier = Modifier.width(2.dp))
        OneCharTextField(
            value = viewModel.secondFieldText.value,
            onValueChanged = {
                if(it.isEmpty()) {
                    if(language == "ar") {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                    else
                        focusManager.moveFocus(FocusDirection.Previous)
                    viewModel.secondFieldText.value = it
                }
                else if (it.length == 1) {
                    if(language == "ar") {
                        focusManager.moveFocus(FocusDirection.Previous)
                    }
                    else
                        focusManager.moveFocus(FocusDirection.Next)
                    viewModel.secondFieldText.value = it
                }
            },
        )
        Spacer(modifier = Modifier.width(2.dp))
        OneCharTextField(
            value = viewModel.thirdFieldText.value,
            onValueChanged = {
                if(it.isEmpty()) {
                    if(language == "ar") {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                    else
                        focusManager.moveFocus(FocusDirection.Previous)
                    viewModel.thirdFieldText.value = it
                }
                else if (it.length == 1) {
                    if(language == "ar") {
                        focusManager.moveFocus(FocusDirection.Previous)
                    }
                    else
                        focusManager.moveFocus(FocusDirection.Next)
                    viewModel.thirdFieldText.value = it
                }
            },
        )
        Spacer(modifier = Modifier.width(2.dp))
        OneCharTextField(
            value = viewModel.fourthFieldText.value,
            onValueChanged = {
                if(it.isEmpty()) {
                    if(language == "ar") {
                        focusManager.clearFocus()
                    }
                    else
                        focusManager.moveFocus(FocusDirection.Previous)
                    viewModel.fourthFieldText.value = it
                }
                else if (it.length == 1) {
                    if(language == "ar") {
                        focusManager.moveFocus(FocusDirection.Previous)
                    }
                    else
                        focusManager.clearFocus()
                    viewModel.fourthFieldText.value = it
                }
            },
        )
    }
}