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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hero.ataa.R
import com.hero.ataa.domain.models.Project
import com.hero.ataa.ui.components.AppBar
import com.hero.ataa.ui.components.ProgressBar
import com.hero.ataa.ui.components.Tag
import com.skydoves.landscapist.coil.CoilImage
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProjectsScreen(
    navController: NavController,
    viewModel: ProjectsViewModel = hiltViewModel()
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            ProjectsAppBar(navController = navController)
        },
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        when (val uiState = viewModel.uiState.value) {
            is ProjectsUiState.Success -> {
                CompositionLocalProvider(
                    LocalOverScrollConfiguration provides null,
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(start = 16.dp, bottom = 16.dp, end = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(13.dp)
                    ) {
                        items(
                            uiState.projects
                        ) { project ->
                            ProjectItem(project = project)
                        }
                    }
                }
            }
            is ProjectsUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(33.dp),
                        color = MaterialTheme.colors.primary,
                    )
                }
            }
            else -> Unit
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
private fun ProjectItem(project: Project) {
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
                    .height(140.dp)
                    .clip(RoundedCornerShape(7.dp)),
                imageModel = project.imageUrl,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = project.title,
                    style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onBackground),
                    modifier = Modifier.weight(1F),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.width(5.dp))
                Tag(title = project.location)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = project.description,
                style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.primaryVariant),
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(10.dp))
            ProgressBar(progress = project.progress, height = 5.dp)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${stringResource(R.string.goal)} ${
                        "%,d".format(
                            Locale.US,
                            project.raisingGoal
                        )
                    } ${stringResource(R.string.currency)}",
                    style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.primary),
                )
                Text(
                    text = project.progress.toString() + "%",
                    style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.primary),
                )
            }
        }
    }
}