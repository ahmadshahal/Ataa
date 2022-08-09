package com.hero.ataa.ui.screens.beneficiary_screen

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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BeneficiaryScreenTwo(
    innerNavController: NavController,
    viewModel: BeneficiaryViewModel,
) {
    val scrollState = rememberScrollState()

    Scaffold(
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
                        if (viewModel.firstFormValidation()) {
                            // TODO:
//                            innerNavController.navigate(Screen.BeneficiaryScreenThree.route)
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
            text = stringResource(id = R.string.location_info),
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
        GovernorateSpinner(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        PlaceSpinner(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        ResidenceSpinner(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        PhoneNumberField(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        AddressField(viewModel = viewModel)
    }
}

@Composable
private fun GovernorateSpinner(viewModel: BeneficiaryViewModel) {
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
private fun PlaceSpinner(viewModel: BeneficiaryViewModel) {
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
private fun ResidenceSpinner(viewModel: BeneficiaryViewModel) {
    val residenceSpinnerExpanded = remember {
        mutableStateOf(false)
    }
    Box {
        Spinner(
            text = viewModel.residenceFieldText.value,
            onClick = {
                residenceSpinnerExpanded.value = true
            },
            isError = viewModel.isErrorResidenceField.value,
            errorMessage = viewModel.residenceFieldErrorMsg.value.asString(),
            hint = stringResource(id = R.string.residence_status)
        )
        DropdownMenu(
            modifier = Modifier.heightIn(max = 300.dp),
            expanded = residenceSpinnerExpanded.value,
            onDismissRequest = {
                residenceSpinnerExpanded.value = false
            }
        ) {
            val ownedStr = stringResource(id = R.string.owned)
            DropdownMenuItem(
                onClick = {
                    viewModel.residenceFieldText.value = ownedStr
                    residenceSpinnerExpanded.value = false
                }
            ) {
                Text(
                    text = stringResource(id = R.string.owned),
                    style = MaterialTheme.typography.body2
                )
            }
            val rentedStr = stringResource(id = R.string.rented)
            DropdownMenuItem(
                onClick = {
                    viewModel.residenceFieldText.value = rentedStr
                    residenceSpinnerExpanded.value = false
                }
            ) {
                Text(
                    text = stringResource(id = R.string.rented),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
private fun PhoneNumberField(viewModel: BeneficiaryViewModel) {
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
private fun AddressField(viewModel: BeneficiaryViewModel) {
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