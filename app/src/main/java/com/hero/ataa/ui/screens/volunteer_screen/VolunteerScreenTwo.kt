package com.hero.ataa.ui.screens.volunteer_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hero.ataa.R
import com.hero.ataa.shared.Constants
import com.hero.ataa.ui.components.*
import com.hero.ataa.ui.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VolunteerScreenTwo(
    innerNavController: NavController,
    viewModel: VolunteerViewModel,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            VolunteerTwoAppBar(innerNavController = innerNavController)
        },
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            ProgressRow(selectedItem = 1)
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
                        Text(
                            text = stringResource(id = R.string.next),
                            style = MaterialTheme.typography.button.copy(MaterialTheme.colors.onPrimary)
                        )
                    },
                    onClick = {
                        if (viewModel.secondFormValidation()) {
                            innerNavController.navigate(Screen.VolunteerScreenThree.route)
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary,
                )
            }
        }
    }
}

@Composable
private fun VolunteerTwoAppBar(innerNavController: NavController) {
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
            text = stringResource(id = R.string.location_info),
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
        GovernorateSpinner(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        PlaceSpinner(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        PhoneNumberField(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        AddressField(viewModel = viewModel)
    }
}

@Composable
private fun GovernorateSpinner(viewModel: VolunteerViewModel) {
    val governorateSpinnerExpanded = remember {
        mutableStateOf(false)
    }
    Box {
        Spinner(
            text = viewModel.governorateFieldText.value,
            onClick = {
                governorateSpinnerExpanded.value = true
            },
            isError = viewModel.isErrorGovernorateField.value,
            errorMessage = viewModel.governorateFieldErrorMsg.value.asString(),
            hint = stringResource(id = R.string.governorate)
        )
        DropdownMenu(
            modifier = Modifier.heightIn(max = 300.dp),
            expanded = governorateSpinnerExpanded.value,
            onDismissRequest = {
                governorateSpinnerExpanded.value = false
            }
        ) {
            Constants.PlacesInSyria.governorates.forEach {
                DropdownMenuItem(
                    onClick = {
                        viewModel.governorateFieldText.value = it
                        governorateSpinnerExpanded.value = false
                    }
                ) {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}

@Composable
private fun PlaceSpinner(viewModel: VolunteerViewModel) {
    val placeSpinnerExpanded = remember {
        mutableStateOf(false)
    }
    Box {
        Spinner(
            text = viewModel.placeFieldText.value,
            onClick = {
                placeSpinnerExpanded.value = true
            },
            isError = viewModel.isErrorPlaceField.value,
            errorMessage = viewModel.placeFieldErrorMsg.value.asString(),
            hint = stringResource(id = R.string.place)
        )
        DropdownMenu(
            modifier = Modifier.heightIn(max = 300.dp),
            expanded = placeSpinnerExpanded.value,
            onDismissRequest = {
                placeSpinnerExpanded.value = false
            }
        ) {
            Constants.PlacesInSyria.places[viewModel.governorateFieldText.value]?.forEach {
                DropdownMenuItem(
                    onClick = {
                        viewModel.placeFieldText.value = it
                        placeSpinnerExpanded.value = false
                    }
                ) {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}

@Composable
private fun PhoneNumberField(viewModel: VolunteerViewModel) {
    val focusManager = LocalFocusManager.current
    RectangularTextField(
        value = viewModel.phoneNumberFieldText.value,
        hint = stringResource(id = R.string.phone_number),
        onValueChanged = {
            viewModel.phoneNumberFieldText.value = it
        },
        isError = viewModel.isErrorPhoneNumberField.value,
        errorMessage = viewModel.phoneNumberFieldErrorMsg.value.asString(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.moveFocus(FocusDirection.Next)
            }
        )
    )
}

@Composable
private fun AddressField(viewModel: VolunteerViewModel) {
    val focusManager = LocalFocusManager.current
    LargeRectangularTextField(
        value = viewModel.addressFieldText.value,
        hint = stringResource(id = R.string.detailed_location),
        onValueChanged = {
            viewModel.addressFieldText.value = it
        },
        isError = viewModel.isErrorAddressField.value,
        errorMessage = viewModel.addressFieldErrorMsg.value.asString(),
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