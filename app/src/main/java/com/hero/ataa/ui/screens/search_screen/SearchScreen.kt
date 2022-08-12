package com.hero.ataa.ui.screens.search_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.hero.ataa.R
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.ui.components.*
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel(),
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
            SearchAppBar(navController = navController, scrollState = lazyListState)
        },
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        when (viewModel.uiState.value) {
            is AllProjectsUiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(start = 16.dp, bottom = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(13.dp),
                    state = lazyListState
                ) {
                    item {
                        SearchTextField(viewModel = viewModel)
                    }
                    item {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            if (viewModel.historyFlow.collectAsState(initial = emptyList<String>()).value.isNotEmpty()) {
                                Text(
                                    text = stringResource(id = R.string.recent_search),
                                    style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onBackground)
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                val language = Locale.getDefault().language
                                FlowRow(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    mainAxisSpacing = 15.dp,
                                    crossAxisSpacing = 10.dp,
                                    mainAxisAlignment = if (language == "ar") FlowMainAxisAlignment.End else FlowMainAxisAlignment.Start
                                ) {
                                    viewModel.historyFlow.collectAsState(initial = emptyList<String>()).value.toList().takeLast(
                                        n = 5
                                    ).forEach {
                                        FilledTag(
                                            title = it,
                                            onClick = {
                                                viewModel.searchFieldText.value = it
                                            }
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                    }
                    when {
                        viewModel.searchResults.value.isEmpty() -> {
                            item {
                                NoResults()
                            }
                        }
                        else -> {
                            items(
                                viewModel.searchResults.value
                            ) { project ->
                                ProjectItem(project = project, navController = navController)
                            }
                        }
                    }
                }
            }
            is AllProjectsUiState.Loading -> {
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
                }
            }
            is AllProjectsUiState.Error -> {
                ErrorWidget(onClick = { viewModel.getProjects() })
            }
        }
    }
}

@Composable
private fun NoResults() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_no_results),
            contentDescription = "",
            modifier = Modifier
                .height(250.dp)
                .width(200.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.no_results_found),
            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onBackground)
        )
        Spacer(modifier = Modifier.height(25.dp))
    }
}

@Composable
private fun SearchAppBar(navController: NavController, scrollState: LazyListState) {
    AppBar(
        title = {
            Text(
                text = stringResource(id = R.string.search),
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

@Composable
private fun SearchTextField(viewModel: SearchViewModel) {
    val focusManager = LocalFocusManager.current
    RectangularTextField(
        height = 42.dp,
        value = viewModel.searchFieldText.value,
        hint = stringResource(id = R.string.search_for_a_project),
        onValueChanged = {
            viewModel.searchFieldText.value = it
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                viewModel.updateHistory()
                focusManager.clearFocus()
            }
        ),
        prefixIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search_icon),
                contentDescription = "",
                modifier = Modifier.size(14.dp),
                tint = MaterialTheme.colors.primary
            )
        }
    )
}
