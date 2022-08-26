package com.hero.ataa.ui.screens.beneficiary_screen

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Today
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
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
import com.hero.ataa.ui.components.*
import com.hero.ataa.ui.navigation.Screen
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BeneficiaryScreenOne(
    outerNavController: NavController,
    innerNavController: NavController,
    viewModel: BeneficiaryViewModel,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            BeneficiaryOneAppBar(outerNavController = outerNavController)
        },
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
            ProgressRow(selectedItem = 0)
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
                        if(viewModel.firstFormValidation()) {
                            innerNavController.navigate(Screen.BeneficiaryScreenTwo.route)
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
private fun BeneficiaryOneAppBar(outerNavController: NavController) {
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
                    outerNavController.popBackStack()
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
            text = stringResource(id = R.string.personal_info),
            style = MaterialTheme.typography.h3.copy(color = MaterialTheme.colors.onBackground)
        )
        Spacer(modifier = Modifier.height(16.dp))
        FieldsColumn(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun FieldsColumn(viewModel: BeneficiaryViewModel) {
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
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        DateOfBirthPicker(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        GenderSpinner(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        SocialStatusSpinner(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        KidsSpinner(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun GenderSpinner(viewModel: BeneficiaryViewModel) {
    val genderSpinnerExpanded = remember {
        mutableStateOf(false)
    }
    Box {
        Spinner(
            text = viewModel.genderFieldText.value,
            onClick = {
                genderSpinnerExpanded.value = true
            },
            isError = viewModel.isErrorGenderField.value,
            errorMessage = viewModel.genderFieldErrorMsg.value.asString(),
            hint = stringResource(id = R.string.gender)
        )
        DropdownMenu(
            expanded = genderSpinnerExpanded.value,
            onDismissRequest = {
                genderSpinnerExpanded.value = false
            }
        ) {
            val maleStr = stringResource(id = R.string.male)
            val femaleStr = stringResource(id = R.string.female)
            DropdownMenuItem(
                onClick = {
                    viewModel.genderFieldText.value = maleStr
                    genderSpinnerExpanded.value = false
                }
            ) {
                Text(
                    text = stringResource(id = R.string.male),
                    style = MaterialTheme.typography.body2
                )
            }
            DropdownMenuItem(
                onClick = {
                    viewModel.genderFieldText.value = femaleStr
                    genderSpinnerExpanded.value = false
                }
            ) {
                Text(
                    text = stringResource(id = R.string.female),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
private fun SocialStatusSpinner(viewModel: BeneficiaryViewModel) {
    val socialStatusSpinnerExpanded = remember {
        mutableStateOf(false)
    }
    Box {
        Spinner(
            text = viewModel.socialStatusFieldText.value,
            onClick = {
                socialStatusSpinnerExpanded.value = true
            },
            isError = viewModel.isErrorSocialStatusField.value,
            errorMessage = viewModel.socialStatusFieldErrorMsg.value.asString(),
            hint = stringResource(id = R.string.social_status)
        )
        DropdownMenu(
            expanded = socialStatusSpinnerExpanded.value,
            onDismissRequest = {
                socialStatusSpinnerExpanded.value = false
            }
        ) {
            val marriedStr = stringResource(id = R.string.married)
            val unmarriedStr = stringResource(id = R.string.unmarried)
            DropdownMenuItem(
                onClick = {
                    viewModel.socialStatusFieldText.value = marriedStr
                    socialStatusSpinnerExpanded.value = false
                }
            ) {
                Text(
                    text = stringResource(id = R.string.married),
                    style = MaterialTheme.typography.body2
                )
            }
            DropdownMenuItem(
                onClick = {
                    viewModel.socialStatusFieldText.value = unmarriedStr
                    socialStatusSpinnerExpanded.value = false
                }
            ) {
                Text(
                    text = stringResource(id = R.string.unmarried),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
private fun KidsSpinner(viewModel: BeneficiaryViewModel) {
    val kidsSpinnerExpanded = remember {
        mutableStateOf(false)
    }
    Box {
        Spinner(
            text = viewModel.kidsFieldText.value,
            onClick = {
                kidsSpinnerExpanded.value = true
            },
            isError = viewModel.isErrorKidsField.value,
            errorMessage = viewModel.kidsFieldErrorMsg.value.asString(),
            hint = stringResource(id = R.string.number_of_kids)
        )
        DropdownMenu(
            modifier = Modifier.heightIn(max = 180.dp),
            expanded = kidsSpinnerExpanded.value,
            onDismissRequest = {
                kidsSpinnerExpanded.value = false
            }
        ) {
            for (i in 0..10) {
                DropdownMenuItem(
                    onClick = {
                        viewModel.kidsFieldText.value = i.toString()
                        kidsSpinnerExpanded.value = false
                    }
                ) {
                    Text(text = i.toString(), style = MaterialTheme.typography.body2)
                }
            }
        }
    }
}

@Composable
private fun DateOfBirthPicker(viewModel: BeneficiaryViewModel) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, dialogYear, dialogMonth, dialogDay ->
            viewModel.dateOfBirthFieldText.value = "$dialogDay/${dialogMonth + 1}/$dialogYear"
        },
        year,
        month,
        day
    )

    Box {
        Spinner(
            text = viewModel.dateOfBirthFieldText.value,
            onClick = {
                  datePickerDialog.show()
            },
            isError = viewModel.isErrorDateOfBirthField.value,
            errorMessage = viewModel.dateOfBirthFieldErrorMsg.value.asString(),
            icon = Icons.Rounded.Today,
            iconSize = 20.dp,
            hint = stringResource(id = R.string.date_of_birth)
        )
    }
}
