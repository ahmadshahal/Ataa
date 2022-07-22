package com.hero.ataa.ui.screens.projects_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hero.ataa.R
import com.hero.ataa.domain.models.Project
import com.hero.ataa.shared.Constants
import com.hero.ataa.ui.components.*
import com.hero.ataa.ui.navigation.Screen
import com.skydoves.landscapist.coil.CoilImage
import java.util.*

@Composable
fun ProjectsScreen(
    navController: NavController,
    viewModel: ProjectsViewModel = hiltViewModel(),
    category: String,
) {
    val lazyListState = rememberLazyListState()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            ProjectsAppBar(navController = navController, category = category, scrollState = lazyListState)
        },
        contentColor = MaterialTheme.colors.onBackground,
    ) {

        when (val uiState = viewModel.uiState.value) {
            is ProjectsUiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(start = 16.dp, bottom = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(13.dp),
                    state = lazyListState
                ) {
                    items(
                        uiState.projects
                    ) { project ->
                        ProjectItem(project = project, navController = navController)
                    }
                }
            }
            is ProjectsUiState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    LoadingDots(
                        circleColor = MaterialTheme.colors.primary,
                        circleSize = 10.dp,
                        spaceBetween = 8.dp,
                        travelDistance = 7.dp
                    )
                    /*
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = stringResource(id = R.string.loading),
                        style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onBackground)
                    )
                     */
                }
            }
            is ProjectsUiState.Error -> {
                ErrorWidget(onClick = { viewModel.getProjects() })
            }
        }
    }
}

@Composable
private fun ProjectsAppBar(navController: NavController, category: String, scrollState: LazyListState) {
    AppBar(
        title = {
            Text(
                text = category,
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
        elevation = if(scrollState.firstVisibleItemScrollOffset > 0) 1.dp else 0.dp
    )
}

@Composable
private fun ProjectItem(project: Project, navController: NavController) {
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
                navController.currentBackStackEntry?.savedStateHandle?.set<Project>(
                    Constants.NavArgs.PROJECT_KEY,
                    project
                )
                navController.navigate(Screen.ProjectScreen.route)
            }
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(7.dp))
                    .background(MaterialTheme.colors.secondaryVariant.copy(0.1F))

            ) {
                CoilImage(
                    modifier = Modifier.fillMaxSize(),
                    imageModel = project.imageUrl,
                    contentScale = ContentScale.Crop,
                )
            }
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
                lineHeight = 18.sp,
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