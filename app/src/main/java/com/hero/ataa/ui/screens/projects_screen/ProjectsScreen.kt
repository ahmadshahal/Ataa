package com.hero.ataa.ui.screens.projects_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hero.ataa.R
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.ui.components.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProjectsScreen(
    navController: NavController,
    viewModel: ProjectsViewModel = hiltViewModel(),
    category: String,
) {
    val lazyListState = rememberLazyListState()
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = uiEvent.message.asString(context),
                    )
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            ProjectsAppBar(
                navController = navController,
                category = category,
                scrollState = lazyListState
            )
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

        when (val uiState = viewModel.uiState.value) {
            is ProjectsUiState.Success -> {
                if(uiState.projects.isEmpty()) {
                    NoResultsWidget(painter = painterResource(id = R.drawable.ic_void))
                }
                else {
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
private fun ProjectsAppBar(
    navController: NavController,
    category: String,
    scrollState: LazyListState
) {
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
        elevation = if (scrollState.firstVisibleItemScrollOffset > 0) 1.dp else 0.dp
    )
}
