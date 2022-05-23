package com.hero.ataa.ui.screens.home_screen

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.VolunteerActivism
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Translate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.hero.ataa.R
import com.hero.ataa.domain.models.Ad
import com.hero.ataa.shared.Constants
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.ui.components.AppBar
import com.hero.ataa.ui.navigation.Screen
import com.skydoves.landscapist.coil.CoilImage
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
    val adsLazyRowState = rememberLazyListState()
    val swipeRefreshState =
        rememberSwipeRefreshState(isRefreshing = viewModel.adsUiState.value is AdsUiState.Loading)

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
            HomeAppBar(scaffoldState = scaffoldState)
        },
        drawerShape = RoundedCornerShape(7.dp),
        drawerBackgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        drawerContent = {
            AppDrawer()
        },
        drawerContentColor = MaterialTheme.colors.onBackground
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.getAds() },
            modifier = Modifier,
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                    scale = true,
                    contentColor = MaterialTheme.colors.primary,
                    backgroundColor = MaterialTheme.colors.surface
                )
            }
        ) {
            // Used to remove over scroll effect.
            CompositionLocalProvider(
                LocalOverScrollConfiguration provides null,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                    // All Composables have 16 horizontal padding, except AdsRow.
                ) {
                    SearchButton()
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        text = stringResource(id = R.string.welcome_to_ataa_for_charity_projects),
                        style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground),
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    when (val adsUiState = viewModel.adsUiState.value) {
                        is AdsUiState.Success -> {
                            AdsLazyRow(adsList = adsUiState.adsList, lazyRowState = adsLazyRowState)
                            Spacer(modifier = Modifier.height(10.dp))
                            AdsBulletsList(
                                length = adsUiState.adsList.size,
                                lazyRowState = adsLazyRowState
                            )
                        }
                        else -> {
                            AdsLoadingLazyRow(lazyRowState = adsLazyRowState)
                            Spacer(modifier = Modifier.height(10.dp))
                            AdsBulletsList(length = 3, lazyRowState = adsLazyRowState)
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        text = stringResource(id = R.string.trending_donations),
                        style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground),
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    MostImportantRow(navController)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        text = stringResource(id = R.string.contribute_with_us),
                        style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground),
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ContributeWithUsRow(navController)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
private fun SearchButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 16.dp)
            .clip(shape = RoundedCornerShape(7.dp))
            .background(MaterialTheme.colors.surface)
            .border(
                width = 0.1.dp,
                color = MaterialTheme.colors.secondaryVariant,
                shape = RoundedCornerShape(7.dp)
            )
            .clickable {
                // TODO.
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search_icon),
                contentDescription = "",
                modifier = Modifier.size(14.dp),
                tint = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F),
                text = stringResource(id = R.string.search_for_a_project),
                style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.primaryVariant),
            )
        }
    }
}

@Composable
private fun HomeAppBar(scaffoldState: ScaffoldState) {
    val coroutineScope = rememberCoroutineScope()
    AppBar(
        title = {
            Text(
                text = stringResource(id = R.string.home),
                style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground),
                textAlign = TextAlign.Center,
            )
        },
        leading = {
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu_icon),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    )
}

@Composable
private fun AppDrawer() {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(MaterialTheme.colors.primary)
        )
        Spacer(modifier = Modifier.height(10.dp))
        DrawerButton(
            text = stringResource(id = R.string.my_account),
            icon = Icons.Rounded.AccountCircle,
        ) {}
        Spacer(modifier = Modifier.height(10.dp))
        DrawerButton(
            text = stringResource(id = R.string.volunteer_with_us),
            icon = Icons.Outlined.VolunteerActivism,
        ) {}
        Spacer(modifier = Modifier.height(10.dp))
        DrawerButton(
            text = stringResource(id = R.string.beneficiary_application),
            icon = Icons.Outlined.Article,
        ) {}
        Spacer(modifier = Modifier.height(10.dp))
        DrawerButton(
            text = stringResource(id = R.string.language),
            icon = Icons.Rounded.Translate,
        ) {}
        Spacer(modifier = Modifier.height(10.dp))
        DrawerButton(
            text = stringResource(id = R.string.about_us),
            icon = Icons.Outlined.Info,
        ) {}
        Spacer(modifier = Modifier.height(10.dp))
        DrawerButton(
            text = stringResource(id = R.string.log_out),
            icon = Icons.Rounded.Logout,
        ) {}
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
private fun DrawerButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "",
                tint = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                modifier = Modifier.weight(1F),
                text = text,
                style = MaterialTheme.typography.subtitle2.copy(color = MaterialTheme.colors.onBackground)
            )
        }
    }
}

@Composable
private fun AdsLazyRow(adsList: List<Ad>, lazyRowState: LazyListState) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = lazyRowState,
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(11.dp),
    ) {
        items(adsList) { ad ->
            AdItem(ad = ad)
        }
    }
}

@Composable
private fun AdsBulletsList(length: Int, lazyRowState: LazyListState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        for (index in 0 until length) {
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .size(5.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(
                        // TODO: Fix Bug here, try landscape mode.
                        if (index == lazyRowState.firstVisibleItemIndex)
                            MaterialTheme.colors.onBackground
                        else
                            MaterialTheme.colors.onBackground.copy(alpha = 0.15F)
                    )
            )
        }
    }
}

@Composable
private fun AdsLoadingLazyRow(lazyRowState: LazyListState) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = lazyRowState,
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(11.dp),
    ) {
        items(3) {
            AdLoadingItem()
        }
    }
}

@Composable
fun AdLoadingItem() {
    Box(
        modifier = Modifier
            .height(180.dp)
            .width(330.dp)
            .clip(RoundedCornerShape((7.dp)))
            .shimmer()
            .background(MaterialTheme.colors.onBackground.copy(0.2F))
    )
}

@Composable
private fun AdItem(ad: Ad) {
    Box(
        modifier = Modifier
            .height(180.dp)
            .width(330.dp)
            .clip(RoundedCornerShape((7.dp)))
            .background(MaterialTheme.colors.onBackground.copy(0.1F))
    ) {
        CoilImage(
            modifier = Modifier.fillMaxSize(),
            imageModel = ad.url,
            contentScale = ContentScale.Crop,
        )
        // TODO: Add Shadow.
        Text(
            text = ad.text,
            style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onPrimary),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(10.dp),
            lineHeight = 20.sp,
        )
    }
}

@Composable
private fun MostImportantRow(navController: NavController) {
    // !: FlowRow doesn't support RTL.
    // TODO: Find a solution.
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        mainAxisSpacing = 9.dp,
        crossAxisSpacing = 9.dp,
        mainAxisAlignment = FlowMainAxisAlignment.End,
    ) {
        CategoryItem(
            name = stringResource(id = R.string.ekhraj_zakat),
            icon = painterResource(id = R.drawable.ic_zakat_icon),
            navController = navController,
            categoryApiKey = Constants.CategoryApiKey.ZAKAT,
        )
        CategoryItem(
            name = stringResource(id = R.string.kafalat_yateem),
            icon = painterResource(id = R.drawable.ic_yateem_icon), navController = navController,
            categoryApiKey = Constants.CategoryApiKey.YATEEM,
        )
        CategoryItem(
            name = stringResource(id = R.string.ramadan_projects),
            icon = painterResource(id = R.drawable.ic_moon_icon),
            navController = navController,
            categoryApiKey = Constants.CategoryApiKey.RAMADAN,
        )
    }
}

@Composable
private fun ContributeWithUsRow(navController: NavController) {
    // !: FlowRow doesn't support RTL.
    // TODO: Find a solution.
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        mainAxisSpacing = 9.dp,
        crossAxisSpacing = 9.dp,
        mainAxisAlignment = FlowMainAxisAlignment.End
    ) {
        CategoryItem(
            name = stringResource(id = R.string.poor_houses),
            icon = painterResource(id = R.drawable.ic_house_icon),
            iconSize = 25.dp,
            navController = navController,
            categoryApiKey = Constants.CategoryApiKey.HOUSES,
        )
        CategoryItem(
            name = stringResource(id = R.string.mosques),
            icon = painterResource(id = R.drawable.ic_mosque_icon),
            iconSize = 25.dp,
            navController = navController,
            categoryApiKey = Constants.CategoryApiKey.MOSQUES,
        )
        CategoryItem(
            name = stringResource(id = R.string.health),
            icon = painterResource(id = R.drawable.ic_health_icon),
            iconSize = 22.dp,
            navController = navController,
            categoryApiKey = Constants.CategoryApiKey.HEALTH,
        )
        CategoryItem(
            name = stringResource(id = R.string.education),
            icon = painterResource(id = R.drawable.ic_education_icon),
            iconSize = 28.dp,
            navController = navController,
            categoryApiKey = Constants.CategoryApiKey.EDUCATION,
        )
    }
}

@Composable
private fun CategoryItem(
    name: String,
    icon: Painter,
    navController: NavController,
    iconSize: Dp = 24.dp,
    categoryApiKey: String,
) {
    Box(
        modifier = Modifier
            .height(103.dp)
            .width(103.dp)
            .clip(RoundedCornerShape((7.dp)))
            .background(MaterialTheme.colors.surface)
            .border(
                width = 0.1.dp,
                color = MaterialTheme.colors.secondaryVariant,
                shape = RoundedCornerShape(7.dp)
            )
            .clickable {
                navController.navigate(Screen.ProjectsScreen.route + "/$name/$categoryApiKey")
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = "",
                modifier = Modifier.size(iconSize),
                tint = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.onBackground),
                textAlign = TextAlign.Center,
                lineHeight = 18.sp
            )
        }
    }
}