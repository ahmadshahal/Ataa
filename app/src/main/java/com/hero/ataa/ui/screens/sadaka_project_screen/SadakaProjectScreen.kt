package com.hero.ataa.ui.screens.sadaka_project_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.hero.ataa.utils.MoneyTransformation

@Composable
fun SadakaProjectScreen(
    navController: NavController,
    viewModel: SadakaProjectViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            SadakaProjectAppBar(navController = navController)
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
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = stringResource(id = R.string.sadaka_quote),
                style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground),
                textAlign = TextAlign.Center,
            )
            ContentColumn(viewModel = viewModel)
            MaterialButton(
                modifier = Modifier.padding(vertical = 16.dp),
                content = {
                    Text(
                        text = stringResource(id = R.string.next),
                        style = MaterialTheme.typography.button.copy(color = MaterialTheme.colors.onPrimary)
                    )
                },
                onClicked = {
                    // TODO.
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
            )
        }
    }
}

@Composable
private fun SadakaProjectAppBar(navController: NavController) {
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
        }
    )
}

@Composable
private fun ContentColumn(viewModel: SadakaProjectViewModel) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitledTextField(
            title = stringResource(id = R.string.optional_amount),
            value = viewModel.moneyFieldText.value,
            onValueChanged = {
                if (it.length <= Constants.MAX_MONEY_DONATION) {
                    viewModel.moneyFieldText.value = it
                    viewModel.chosenAmountIdx.value = -1
                }
            },
            visualTransformation = MoneyTransformation(currency = stringResource(id = R.string.currency)),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
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
                isSelected = viewModel.chosenAmountIdx.value == 0,
                onClick = {
                    viewModel.chosenAmountIdx.value = 0
                    viewModel.moneyFieldText.value = "10000"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._25k),
                isSelected = viewModel.chosenAmountIdx.value == 1,
                onClick = {
                    viewModel.chosenAmountIdx.value = 1
                    viewModel.moneyFieldText.value = "25000"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._50k),
                isSelected = viewModel.chosenAmountIdx.value == 2,
                onClick = {
                    viewModel.chosenAmountIdx.value = 2
                    viewModel.moneyFieldText.value = "50000"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._100k),
                isSelected = viewModel.chosenAmountIdx.value == 3,
                onClick = {
                    viewModel.chosenAmountIdx.value = 3
                    viewModel.moneyFieldText.value = "100000"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._250k),
                isSelected = viewModel.chosenAmountIdx.value == 4,
                onClick = {
                    viewModel.chosenAmountIdx.value = 4
                    viewModel.moneyFieldText.value = "250000"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._500k),
                isSelected = viewModel.chosenAmountIdx.value == 5,
                onClick = {
                    viewModel.chosenAmountIdx.value = 5
                    viewModel.moneyFieldText.value = "500000"
                }
            )
        }
    }
}