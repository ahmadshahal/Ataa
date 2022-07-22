package com.hero.ataa.ui.screens.payment_screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hero.ataa.R
import com.hero.ataa.ui.components.AppBar
import com.hero.ataa.ui.components.MaterialButton
import com.hero.ataa.ui.components.RectangularRadioButton

@Composable
fun PaymentScreen(
    navController: NavController,
    viewModel: PaymentViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            PaymentAppBar(navController = navController, scrollState = scrollState)
        },
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            ContentColumn(viewModel = viewModel)
            MaterialButton(
                modifier = Modifier.padding(vertical = 16.dp),
                content = {
                    Text(
                        text = stringResource(id = R.string.just_donate),
                        style = MaterialTheme.typography.button.copy(color = MaterialTheme.colors.onPrimary)
                    )
                },
                onClick = {
                    // TODO.
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
            )
        }
    }
}

@Composable
private fun PaymentAppBar(navController: NavController, scrollState: ScrollState) {
    AppBar(
        title = {
            Text(
                text = stringResource(id = R.string.payment),
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
private fun ContentColumn(viewModel: PaymentViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        RectangularRadioButton(
            text = stringResource(id = R.string.paypal),
            painter = painterResource(id = R.drawable.ic_paypal),
            isSelected = viewModel.chosenIdx.value == 0,
            iconWidth = 30.dp,
            iconHeight = 30.dp
        ) {
            viewModel.chosenIdx.value = 0
        }
        Spacer(modifier = Modifier.height(20.dp))
        RectangularRadioButton(
            text = stringResource(id = R.string.master_card),
            painter = painterResource(id = R.drawable.ic_mastercard_logo),
            isSelected = viewModel.chosenIdx.value == 1,
            iconWidth = 30.dp,
            iconHeight = 30.dp
        ) {
            viewModel.chosenIdx.value = 1
        }
    }
}