package com.hero.ataa.ui.screens.language_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hero.ataa.R
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.ui.components.AppBar
import com.hero.ataa.ui.components.MaterialButton
import com.hero.ataa.ui.components.RectangularRadioButton

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LanguageScreen(
    navController: NavController,
    viewModel: LanguageViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.PopBackStack -> {
                    navController.popBackStack()
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            LanguageAppBar(navController = navController, scrollState = scrollState)
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
                    if(viewModel.loading.value) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.5.dp
                        )
                    }
                    else {
                        Text(
                            text = stringResource(id = R.string.save),
                            style = MaterialTheme.typography.button.copy(color = MaterialTheme.colors.onPrimary)
                        )
                    }
                },
                onClick = {
                    viewModel.save()
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                enabled = !viewModel.loading.value
            )
        }
    }
}

@Composable
private fun LanguageAppBar(navController: NavController, scrollState: ScrollState) {
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
        },
        elevation = if(scrollState.value > 0) 1.dp else 0.dp
    )
}

@Composable
private fun ContentColumn(viewModel: LanguageViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        RectangularRadioButton(
            text = stringResource(id = R.string.english),
            painter = painterResource(id = R.drawable.ic_united_states_flag),
            isSelected = !viewModel.isArabic.value
        ) {
            viewModel.isArabic.value = false
        }
        Spacer(modifier = Modifier.height(20.dp))
        RectangularRadioButton(
            text = stringResource(id = R.string.arabic),
            painter = painterResource(id = R.drawable.syria_flag),
            isSelected = viewModel.isArabic.value
        ) {
            viewModel.isArabic.value = true
        }
    }
}