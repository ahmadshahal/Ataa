package com.hero.ataa.ui.screens.settings_screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Translate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hero.ataa.MainViewModel
import com.hero.ataa.R
import com.hero.ataa.ui.components.AppBar
import com.hero.ataa.ui.components.SettingsButton
import com.hero.ataa.ui.navigation.Screen

@Composable
fun SettingsScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val scrollState = rememberScrollState()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            SettingsAppBar(navController = navController, scrollState = scrollState)
        },
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            SettingsButton(
                prefix = {
                    Icon(
                        Icons.Rounded.Translate,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = ""
                    )
                },
                text = stringResource(id = R.string.language),
                onClick = {
                    navController.navigate(Screen.LanguageScreen.route + "/${mainViewModel.isArabic}")
                },
                clickable = true,
            )
            Spacer(modifier = Modifier.height(16.dp))
            SettingsButton(
                prefix = {
                    Icon(
                        Icons.Rounded.DarkMode,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = ""
                    )
                },
                text = stringResource(id = R.string.dark_mode),
                suffix = {
                    Switch(
                        checked = mainViewModel.isDarkMode.value,
                        onCheckedChange = {
                            mainViewModel.isDarkMode.value = it
                        },
                        modifier = Modifier,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = MaterialTheme.colors.primary,
                            checkedTrackColor = MaterialTheme.colors.primary,
                            checkedTrackAlpha = 0.3F,
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            SettingsButton(
                prefix = {
                    Icon(
                        Icons.Rounded.Notifications,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = ""
                    )
                },
                text = stringResource(id = R.string.notifications),
                suffix = {
                    Switch(
                        checked = mainViewModel.notificationsON.value,
                        onCheckedChange = {
                            mainViewModel.notificationsON.value = it
                        },
                        modifier = Modifier,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = MaterialTheme.colors.primary,
                            checkedTrackColor = MaterialTheme.colors.primary,
                            checkedTrackAlpha = 0.3F,
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun SettingsAppBar(navController: NavController, scrollState: ScrollState) {
    AppBar(
        title = {
            Text(
                text = stringResource(id = R.string.settings),
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