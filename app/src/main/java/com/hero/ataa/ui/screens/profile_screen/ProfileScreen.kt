package com.hero.ataa.ui.screens.profile_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.AlternateEmail
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hero.ataa.MainViewModel
import com.hero.ataa.R
import com.hero.ataa.ui.components.AppBar
import com.hero.ataa.ui.components.SettingsButton

@Composable
fun ProfileScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val scrollState = rememberScrollState()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            ProfileAppBar(navController = navController)
        },
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(60.dp))
                    .border(
                        shape = RoundedCornerShape(60.dp),
                        color = MaterialTheme.colors.primary,
                        width = 2.dp
                    )
                    .background(MaterialTheme.colors.surface),
            ) {
                Icon(
                    imageVector = Icons.Rounded.AccountCircle,
                    contentDescription = "",
                    tint = MaterialTheme.colors.primaryVariant.copy(alpha = 0.2F),
                    modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Ahmad Al-Shahal",
                style = MaterialTheme.typography.h3.copy(color = MaterialTheme.colors.onBackground),
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "ahmad.alshahal2@gmail.com",
                style = MaterialTheme.typography.subtitle2.copy(
                    color = MaterialTheme.colors.onBackground,
                    fontSize = 14.sp
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(25.dp))
            SettingsButton(
                prefix = {
                    Icon(
                        Icons.Outlined.Article,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = ""
                    )
                },
                text = stringResource(id = R.string.donations_receipts),
                onClick = {
                },
                clickable = true,
                height = 55.dp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            SettingsButton(
                prefix = {
                    Icon(
                        Icons.Rounded.AlternateEmail,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = ""
                    )
                },
                text = stringResource(id = R.string.edit_profile),
                onClick = {
                },
                clickable = true,
                height = 55.dp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            SettingsButton(
                prefix = {
                    Icon(
                        Icons.Rounded.Logout,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = ""
                    )
                },
                text = stringResource(id = R.string.log_out),
                onClick = {
                },
                clickable = true,
                height = 55.dp,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun ProfileAppBar(navController: NavController) {
    AppBar(
        title = {
            Text(
                text = stringResource(id = R.string.my_account),
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