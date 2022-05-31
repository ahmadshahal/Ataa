package com.hero.ataa.ui.screens.language_screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hero.ataa.MainViewModel
import com.hero.ataa.R
import com.hero.ataa.ui.components.AppBar
import com.hero.ataa.ui.components.AtaaRadioButton
import com.hero.ataa.ui.components.MaterialButton
import com.hero.ataa.utils.findActivity

@Composable
fun LanguageScreen(
    navController: NavController,
    viewModel: LanguageViewModel = viewModel(),
    mainViewModel: MainViewModel
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            LanguageAppBar(navController = navController)
        },
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            ContentColumn(scrollState = scrollState, viewModel = viewModel)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            ) {
                MaterialButton(
                    content = {
                        Text(
                            text = stringResource(id = R.string.save),
                            style = MaterialTheme.typography.button.copy(color = MaterialTheme.colors.onPrimary)
                        )
                    },
                    onClicked = {
                        if(mainViewModel.isArabic != viewModel.isArabic.value) {
                            mainViewModel.isArabic = viewModel.isArabic.value
                            context.findActivity()?.recreate()
                        }
                        navController.popBackStack()
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary,
                )
            }
        }
    }
}

@Composable
private fun LanguageAppBar(navController: NavController) {
    AppBar(
        title = {
            Text(
                text = stringResource(id = R.string.language),
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
private fun ContentColumn(scrollState: ScrollState, viewModel: LanguageViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        AtaaRadioButton(
            text = stringResource(id = R.string.english),
            painter = painterResource(id = R.drawable.ic_united_states_flag),
            isSelected = !viewModel.isArabic.value
        ) {
            viewModel.isArabic.value = false
        }
        Spacer(modifier = Modifier.height(20.dp))
        AtaaRadioButton(
            text = stringResource(id = R.string.arabic),
            painter = painterResource(id = R.drawable.syria_flag),
            isSelected = viewModel.isArabic.value
        ) {
            viewModel.isArabic.value = true
        }
        Spacer(modifier = Modifier.height(75.dp))
    }
}