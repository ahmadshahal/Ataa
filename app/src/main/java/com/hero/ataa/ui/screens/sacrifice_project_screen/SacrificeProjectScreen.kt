package com.hero.ataa.ui.screens.sacrifice_project_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.hero.ataa.ui.components.*
import com.hero.ataa.ui.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SacrificeProjectScreen(
    navController: NavController,
    viewModel: SacrificeViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            SacrificeProjectAppBar(navController = navController, scrollState = scrollState)
        },
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        when(val uiState = viewModel.uiState.value) {
            is SacrificeUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    ContentColumn(viewModel = viewModel, sacrificeValue = uiState.sacrificeValue)
                    MaterialButton(
                        modifier = Modifier.padding(vertical = 16.dp),
                        content = {
                            Text(
                                text = stringResource(id = R.string.next),
                                style = MaterialTheme.typography.button.copy(color = MaterialTheme.colors.onPrimary)
                            )
                        },
                        onClick = {
                            if(viewModel.number.value.toIntOrNull() ?: 0 != 0) {
                                navController.navigate(Screen.PaymentScreen.route + "/${(viewModel.number.value.toIntOrNull() ?: 0) * uiState.sacrificeValue}/${Constants.PermanentProjectId.SACRIFICE}")
                            }
                        },
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.onPrimary,
                    )
                }
            }
            is SacrificeUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    LoadingDots(
                        modifier = Modifier.align(Alignment.Center),
                        circleColor = MaterialTheme.colors.primary,
                        circleSize = 10.dp,
                        spaceBetween = 8.dp,
                        travelDistance = 7.dp
                    )
                }
            }
            is SacrificeUiState.Error -> {
                ErrorWidget(onClick = { viewModel.getSacrificeValue() })
            }
        }
    }
}

@Composable
private fun SacrificeProjectAppBar(navController: NavController, scrollState: ScrollState) {
    AppBar(
        title = {
            Text(
                text = stringResource(id = R.string.sacrifice),
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
private fun ContentColumn(viewModel: SacrificeViewModel, sacrificeValue: Int) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.sacrifice_quote),
            style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onBackground),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(20.dp))
        TitledTextField(
            title = stringResource(id = R.string.optional_number),
            value = viewModel.number.value,
            onValueChanged = {
                if (it.length <= Constants.MAX_SACRIFICE) {
                    viewModel.number.value = it
                    viewModel.chosenNumberIdx.value = -1
                }
            },
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
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
                text = stringResource(id = R.string._1_sacrifice),
                isSelected = viewModel.chosenNumberIdx.value == 0,
                onClick = {
                    viewModel.chosenNumberIdx.value = 0
                    viewModel.number.value = "1"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._2_sacrifices),
                isSelected = viewModel.chosenNumberIdx.value == 1,
                onClick = {
                    viewModel.chosenNumberIdx.value = 1
                    viewModel.number.value = "2"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._3_sacrifices),
                isSelected = viewModel.chosenNumberIdx.value == 2,
                onClick = {
                    viewModel.chosenNumberIdx.value = 2
                    viewModel.number.value = "3"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._4_sacrifices),
                isSelected = viewModel.chosenNumberIdx.value == 3,
                onClick = {
                    viewModel.chosenNumberIdx.value = 3
                    viewModel.number.value = "4"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._5_sacrifices),
                isSelected = viewModel.chosenNumberIdx.value == 4,
                onClick = {
                    viewModel.chosenNumberIdx.value = 4
                    viewModel.number.value = "5"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._6_sacrifices),
                isSelected = viewModel.chosenNumberIdx.value == 5,
                onClick = {
                    viewModel.chosenNumberIdx.value = 5
                    viewModel.number.value = "6"
                }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        TotalBox(
            total = ((viewModel.number.value.toIntOrNull() ?: 0) * sacrificeValue).toString(),
            currency = stringResource(id = R.string.currency)
        )
    }
}