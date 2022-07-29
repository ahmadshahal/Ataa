package com.hero.ataa.ui.screens.edit_profile_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DriveFileRenameOutline
import androidx.compose.material.icons.rounded.Password
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
import com.hero.ataa.ui.components.ProfileButton
import com.hero.ataa.ui.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditProfileScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            EditProfileAppBar(navController = navController, scrollState = scrollState)
        },
        contentColor = MaterialTheme.colors.onBackground,
        snackbarHost = { state ->
            SnackbarHost(state) { data ->
                Snackbar(
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = MaterialTheme.colors.onSecondary,
                    snackbarData = data,
                )
            }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            ProfileButton(
                prefix = {
                    Icon(
                        Icons.Rounded.DriveFileRenameOutline,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = ""
                    )
                },
                text = stringResource(id = R.string.edit_name),
                onClick = {
                    navController.navigate(Screen.EditNameScreen.route + "/Ahmad Al-Shahal")
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfileButton(
                prefix = {
                    Icon(
                        Icons.Rounded.Password,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = ""
                    )
                },
                text = stringResource(id = R.string.edit_password),
                onClick = {
                    navController.navigate(Screen.EditPasswordScreen.route)
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun EditProfileAppBar(navController: NavController, scrollState: ScrollState) {
    AppBar(
        title = {
            Text(
                text = stringResource(id = R.string.edit_profile),
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