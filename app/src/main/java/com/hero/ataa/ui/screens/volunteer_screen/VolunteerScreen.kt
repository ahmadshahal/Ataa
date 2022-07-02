package com.hero.ataa.ui.screens.volunteer_screen

import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hero.ataa.R
import com.hero.ataa.ui.components.AppBar
import com.hero.ataa.ui.components.MaterialButton
import com.hero.ataa.ui.components.OutlinedMaterialButton

@Composable
fun VolunteerScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            VolunteerAppBar(navController = navController)
        },
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp),
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            TitleRow()
            Spacer(modifier = Modifier.height(30.dp))
            ButtonsRow()
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = stringResource(id = R.string.why_do_we_volunteer),
                style = MaterialTheme.typography.h3.copy(color = MaterialTheme.colors.onBackground),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.why_we_volunteer_text),
                style = MaterialTheme.typography.overline.copy(color = MaterialTheme.colors.primaryVariant),
                lineHeight = 21.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun VolunteerAppBar(navController: NavController) {
    AppBar(
        title = {
            Text(
                text = stringResource(id = R.string.volunteer_with_us),
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
private fun TitleRow() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_volunteer),
            contentDescription = "",
            modifier = Modifier
                .height(130.dp)
                .weight(1F)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            modifier = Modifier.weight(0.7F),
            text = stringResource(id = R.string.we_rise_by_lifting_others),
            style = MaterialTheme.typography.h2.copy(color = MaterialTheme.colors.primary),
            lineHeight = 35.sp
        )
    }
}

@Composable
private fun ButtonsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MaterialButton(
            modifier = Modifier.weight(1F),
            content = {
                Text(
                    text = stringResource(id = R.string.volunteer_now),
                    style = MaterialTheme.typography.button.copy(color = MaterialTheme.colors.onPrimary)
                )
            },
            onClick = { /*TODO*/ },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        )
        Spacer(modifier = Modifier.width(25.dp))
        OutlinedMaterialButton(
            modifier = Modifier.weight(1F),
            content = {
                Text(
                    text = stringResource(id = R.string.check_our_projects),
                    style = MaterialTheme.typography.button.copy(color = MaterialTheme.colors.primary)
                )
            },
            onClicked = { /*TODO*/ },
            backgroundColor = MaterialTheme.colors.primary,
        )
    }
}