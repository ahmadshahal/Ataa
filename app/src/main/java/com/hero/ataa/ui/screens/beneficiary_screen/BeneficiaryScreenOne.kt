package com.hero.ataa.ui.screens.beneficiary_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Article
import androidx.compose.material.icons.rounded.Feed
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hero.ataa.R
import com.hero.ataa.ui.components.MaterialButton
import com.hero.ataa.ui.components.ProgressItem
import com.hero.ataa.ui.components.RectangularTextField
import com.hero.ataa.ui.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BeneficiaryScreenOne(
    outerNavController: NavController,
    innerNavController: NavController,
    viewModel: BeneficiaryViewModel,
) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            BeneficiaryOneAppBar(outerNavController = outerNavController, scrollState = scrollState)
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
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ContentColumn(viewModel = viewModel)
            MaterialButton(
                content = {
                    Text(
                        text = stringResource(id = R.string.next),
                        style = MaterialTheme.typography.button.copy(MaterialTheme.colors.onPrimary)
                    )
                },
                onClick = {
                    // TODO: Validation
                    innerNavController.navigate(Screen.BeneficiaryScreenTwo.route)
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
            )
        }
    }
}

@Composable
private fun BeneficiaryOneAppBar(outerNavController: NavController, scrollState: ScrollState) {
    com.hero.ataa.ui.components.AppBar(
        title = {
            Text(
                text = stringResource(id = com.hero.ataa.R.string.beneficiary_form),
                style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground),
                textAlign = TextAlign.Center,
            )
        },
        leading = {
            IconButton(
                onClick = {
                    outerNavController.popBackStack()
                }
            ) {
                Icon(
                    painter = painterResource(id = com.hero.ataa.R.drawable.ic_back_icon),
                    contentDescription = "",
                    modifier = Modifier.size(19.dp),
                    tint = MaterialTheme.colors.onBackground
                )
            }
        },
        elevation = if (scrollState.value > 0) 1.dp else 0.dp
    )
}

@Composable
private fun ContentColumn(viewModel: BeneficiaryViewModel) {
    Column {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            ProgressItem(
                imageVector = Icons.Rounded.Article,
                isSelected = true,
                modifier = Modifier.weight(1F)
            )
            Spacer(modifier = Modifier.width(8.dp))
            ProgressItem(
                imageVector = Icons.Rounded.LocationOn,
                isSelected = false,
                modifier = Modifier.weight(1F)
            )
            Spacer(modifier = Modifier.width(8.dp))
            ProgressItem(
                imageVector = Icons.Rounded.Feed,
                isSelected = false,
                modifier = Modifier.weight(1F)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.personal_info),
            style = MaterialTheme.typography.h3.copy(color = MaterialTheme.colors.onBackground)
        )
        Spacer(modifier = Modifier.height(16.dp))
        FieldsColumn(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun FieldsColumn(viewModel: BeneficiaryViewModel) {
    val focusManager = LocalFocusManager.current
    Column {
        RectangularTextField(
            value = viewModel.fullNameFieldText.value,
            hint = stringResource(id = R.string.full_name),
            onValueChanged = {
                viewModel.fullNameFieldText.value = it
            },
            isError = viewModel.isErrorNameField.value,
            errorMessage = viewModel.nameFieldErrorMsg.value.asString(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        RectangularTextField(
            value = viewModel.nationalNumberFieldText.value,
            hint = stringResource(id = R.string.national_number),
            onValueChanged = {
                viewModel.nationalNumberFieldText.value = it
            },
            isError = viewModel.isErrorNationalNumberField.value,
            errorMessage = viewModel.nationalNumberFieldErrorMsg.value.asString(),
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
}
