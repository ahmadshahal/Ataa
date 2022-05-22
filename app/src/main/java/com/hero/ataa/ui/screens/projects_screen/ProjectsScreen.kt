package com.hero.ataa.ui.screens.projects_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hero.ataa.R
import com.hero.ataa.domain.models.Project
import com.hero.ataa.ui.components.AppBar
import com.skydoves.landscapist.coil.CoilImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProjectsScreen(
    navController: NavController,
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { state ->
            SnackbarHost(state) { data ->
                Snackbar(
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = MaterialTheme.colors.onSecondary,
                    snackbarData = data,
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            ProjectsAppBar(navController = navController)
        },
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        CompositionLocalProvider(
            LocalOverScrollConfiguration provides null,
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(13.dp)
            ) {
                items(
                    listOf(Project(), Project(), Project(), Project())
                ) { project ->
                    ProjectItem(project = project)
                }
            }
        }
    }
}

@Composable
private fun ProjectsAppBar(navController: NavController) {
    AppBar(
        title = {
            Text(
                text = "تعليم",
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
fun ProjectItem(project: Project) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(7.dp))
            .background(MaterialTheme.colors.surface)
            .border(
                width = 0.1.dp,
                color = MaterialTheme.colors.secondaryVariant,
                shape = RoundedCornerShape(7.dp)
            )
            .clickable {
                // TODO.
            }
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            CoilImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(7.dp)),
                imageModel = project.imageUrl,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = project.title,
                    style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onBackground),
                    modifier = Modifier.weight(1F),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.width(5.dp))
                // TODO: Tag
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = project.description,
                style = MaterialTheme.typography.overline.copy(color = MaterialTheme.colors.primaryVariant),
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}