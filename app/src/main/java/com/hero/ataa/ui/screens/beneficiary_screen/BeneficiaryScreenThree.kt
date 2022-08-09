package com.hero.ataa.ui.screens.beneficiary_screen

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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BeneficiaryScreenThree(
    innerNavController: NavController,
    viewModel: BeneficiaryViewModel,
    outerNavController: NavController
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
            BeneficiaryTwoAppBar(innerNavController = innerNavController)
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
private fun BeneficiaryTwoAppBar(innerNavController: NavController) {
    AppBar(
        title = {
            Text(
                text = stringResource(id = R.string.beneficiary_form),
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
private fun ContentColumn(viewModel: BeneficiaryViewModel) {
    Column {
        Text(
            text = stringResource(id = R.string.work_and_health_info),
            style = MaterialTheme.typography.h3.copy(color = MaterialTheme.colors.onBackground)
        )
        Spacer(modifier = Modifier.height(16.dp))
        FieldsColumn(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun FieldsColumn(viewModel: BeneficiaryViewModel) {
    Column {
        WorkField(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        IncomeField(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        HealthField(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        AboutField(viewModel = viewModel)
    }
}

@Composable
private fun WorkField(viewModel: BeneficiaryViewModel) {
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
private fun IncomeField(viewModel: BeneficiaryViewModel) {
    val focusManager = LocalFocusManager.current
    RectangularTextField(
        value = viewModel.incomeFieldText.value,
        hint = stringResource(id = R.string.monthly_income),
        onValueChanged = {
            viewModel.incomeFieldText.value = it
        },
        isError = viewModel.isErrorIncomeField.value,
        errorMessage = viewModel.incomeFieldErrorMsg.value.asString(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
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
private fun HealthField(viewModel: BeneficiaryViewModel) {
    val focusManager = LocalFocusManager.current
    LargeRectangularTextField(
        value = viewModel.healthFieldText.value,
        hint = stringResource(id = R.string.health_status),
        onValueChanged = {
            viewModel.healthFieldText.value = it
        },
        isError = viewModel.isErrorHealthField.value,
        errorMessage = viewModel.healthFieldErrorMsg.value.asString(),
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
private fun AboutField(viewModel: BeneficiaryViewModel) {
    val focusManager = LocalFocusManager.current
    LargeRectangularTextField(
        value = viewModel.aboutFieldText.value,
        hint = stringResource(id = R.string.about_beneficiary),
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