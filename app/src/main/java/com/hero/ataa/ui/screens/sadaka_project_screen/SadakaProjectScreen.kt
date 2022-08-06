package com.hero.ataa.ui.screens.sadaka_project_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.hero.ataa.R
import com.hero.ataa.shared.Constants
import com.hero.ataa.ui.components.AppBar
import com.hero.ataa.ui.components.MaterialButton
import com.hero.ataa.ui.components.SquaredRadioButton
import com.hero.ataa.ui.components.TitledTextField
import com.hero.ataa.ui.navigation.Screen
import com.hero.ataa.utils.MoneyTransformation

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SadakaProjectScreen(
    navController: NavController,
    viewModel: SadakaViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            SadakaProjectAppBar(navController = navController, scrollState = scrollState)
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
            ContentColumn(chosenAmountIdx = viewModel.chosenAmountIdx, amount = viewModel.amount)
            MaterialButton(
                modifier = Modifier.padding(vertical = 16.dp),
                content = {
                    Text(
                        text = stringResource(id = R.string.next),
                        style = MaterialTheme.typography.button.copy(color = MaterialTheme.colors.onPrimary)
                    )
                },
                onClick = {
                    if(viewModel.amount.value.toIntOrNull() ?: 0 != 0) {
                        navController.navigate(Screen.PaymentScreen.route + "/${(viewModel.amount.value.toIntOrNull() ?: 0)}/${Constants.PermanentProjectId.SADAKA}")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
            )
        }
    }
}

@Composable
private fun SadakaProjectAppBar(navController: NavController, scrollState: ScrollState) {
    AppBar(
        title = {
            Text(
                text = stringResource(id = R.string.sadaka),
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
private fun ContentColumn(chosenAmountIdx: MutableState<Int>, amount: MutableState<String>) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.sadaka_quote),
            style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onBackground),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(30.dp))
        TitledTextField(
            title = stringResource(id = R.string.optional_amount),
            value = amount.value,
            onValueChanged = {
                if (it.length <= Constants.MAX_MONEY_DONATION) {
                    amount.value = it
                    chosenAmountIdx.value = -1
                }
            },
            visualTransformation = MoneyTransformation(currency = stringResource(id = R.string.currency)),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
        )
        Spacer(modifier = Modifier.height(20.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSpacing = 10.dp,
            crossAxisSpacing = 15.dp,
            mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
            crossAxisAlignment = FlowCrossAxisAlignment.Center
        ) {
            SquaredRadioButton(
                text = stringResource(id = R.string._10k),
                isSelected = chosenAmountIdx.value == 0,
                onClick = {
                    chosenAmountIdx.value = 0
                    amount.value = "10000"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._25k),
                isSelected = chosenAmountIdx.value == 1,
                onClick = {
                    chosenAmountIdx.value = 1
                    amount.value = "25000"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._50k),
                isSelected = chosenAmountIdx.value == 2,
                onClick = {
                    chosenAmountIdx.value = 2
                    amount.value = "50000"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._100k),
                isSelected = chosenAmountIdx.value == 3,
                onClick = {
                    chosenAmountIdx.value = 3
                    amount.value = "100000"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._250k),
                isSelected = chosenAmountIdx.value == 4,
                onClick = {
                    chosenAmountIdx.value = 4
                    amount.value = "250000"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._500k),
                isSelected = chosenAmountIdx.value == 5,
                onClick = {
                    chosenAmountIdx.value = 5
                    amount.value = "500000"
                }
            )
        }
    }
}