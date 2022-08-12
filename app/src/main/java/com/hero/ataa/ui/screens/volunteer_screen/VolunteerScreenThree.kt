package com.hero.ataa.ui.screens.volunteer_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hero.ataa.R
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.ui.components.*
import com.hero.ataa.ui.screens.beneficiary_screen.FormUiState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VolunteerScreenThree(
    innerNavController: NavController,
    outerNavController: NavController,
    viewModel: VolunteerViewModel,
) {
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = uiEvent.message.asString(context),
                    )
                }
                is UiEvent.PopBackStack -> {
                    outerNavController.popBackStack()
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { state ->
            SnackbarHost(state) { data ->
                Snackbar(
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = MaterialTheme.colors.onSecondary,
                    snackbarData = data,
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            VolunteerThreeAppBar(innerNavController = innerNavController)
        },
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            ProgressRow(selectedItem = 2)
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ContentColumn(viewModel = viewModel)
                MaterialButton(
                    modifier = Modifier.padding(bottom = 16.dp),
                    content = {
                        if (viewModel.uiState.value is FormUiState.Initial) {
                            Text(
                                text = stringResource(id = R.string.done),
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
                    onClick = {
                        if (viewModel.thirdFormValidation())
                            viewModel.register()
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary,
                    enabled = viewModel.uiState.value is FormUiState.Initial
                )
            }
        }
    }
}

@Composable
private fun VolunteerThreeAppBar(innerNavController: NavController) {
    AppBar(
        title = {
            Text(
                text = stringResource(id = R.string.volunteer_form),
                style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground),
                textAlign = TextAlign.Center,
            )
        },
        leading = {
            IconButton(
                onClick = {
                    innerNavController.popBackStack()
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
    )
}

@Composable
private fun ContentColumn(viewModel: VolunteerViewModel) {
    Column {
        Text(
            text = stringResource(id = R.string.work_info),
            style = MaterialTheme.typography.h3.copy(color = MaterialTheme.colors.onBackground)
        )
        Spacer(modifier = Modifier.height(16.dp))
        FieldsColumn(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun FieldsColumn(viewModel: VolunteerViewModel) {
    Column {
        WorkField(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        AboutField(viewModel = viewModel)
    }
}

@Composable
private fun WorkField(viewModel: VolunteerViewModel) {
    val focusManager = LocalFocusManager.current
    RectangularTextField(
        value = viewModel.workFieldText.value,
        hint = stringResource(id = R.string.current_work),
        onValueChanged = {
            viewModel.workFieldText.value = it
        },
        isError = viewModel.isErrorWorkField.value,
        errorMessage = viewModel.workFieldErrorMsg.value.asString(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Next)
            }
        )
    )
}

@Composable
private fun AboutField(viewModel: VolunteerViewModel) {
    val focusManager = LocalFocusManager.current
    LargeRectangularTextField(
        value = viewModel.aboutFieldText.value,
        hint = stringResource(id = R.string.volunteer_exp),
        onValueChanged = {
            viewModel.aboutFieldText.value = it
        },
        isError = viewModel.isErrorAboutField.value,
        errorMessage = viewModel.aboutFieldErrorMsg.value.asString(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        )
    )
}
