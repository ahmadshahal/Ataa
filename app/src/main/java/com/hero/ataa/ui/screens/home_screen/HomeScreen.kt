package com.hero.ataa.ui.screens.home_screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
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
import com.hero.ataa.R
import com.hero.ataa.domain.models.Ad
import com.hero.ataa.ui.components.AppBar
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
    val adsLazyRowState = rememberLazyListState()

    Scaffold(
        scaffoldState = scaffoldState,
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(vertical = 16.dp)
            // Vertical Padding, all other Composables have 16 horizontal padding, except AdsRow.
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = stringResource(id = R.string.welcome_to_ataa_for_charity_projects),
                style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground),
            )
            Spacer(modifier = Modifier.height(10.dp))
            AdsLazyRow(viewModel = viewModel, lazyRowState = adsLazyRowState)
            Spacer(modifier = Modifier.height(10.dp))
            AdsBulletsList(viewModel = viewModel, lazyRowState = adsLazyRowState)
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = stringResource(id = R.string.trending_donations),
                style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground),
            )
            Spacer(modifier = Modifier.height(10.dp))
            MostImportantRow()
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = stringResource(id = R.string.contribute_with_us),
                style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground),
            )
            Spacer(modifier = Modifier.height(10.dp))
            ContributeWithUsRow()
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
        Spacer(modifier = Modifier.height(20.dp))
        DrawerButton(
            text = stringResource(id = R.string.my_account),
            iconPainter = painterResource(id = R.drawable.ic_profile_icon_2)
        ) {}
        Spacer(modifier = Modifier.height(20.dp))
        DrawerButton(
            text = stringResource(id = R.string.volunteering_application),
            iconPainter = painterResource(id = R.drawable.ic_document1_icon)
        ) {}
        Spacer(modifier = Modifier.height(20.dp))
        DrawerButton(
            text = stringResource(id = R.string.beneficiary_application),
            iconPainter = painterResource(id = R.drawable.ic_document2_icon),
            iconSize = 22.dp
        ) {}
        Spacer(modifier = Modifier.height(20.dp))
        DrawerButton(
            text = stringResource(id = R.string.language),
            iconPainter = painterResource(id = R.drawable.ic_language_icon)
        ) {}
        Spacer(modifier = Modifier.height(20.dp))
        DrawerButton(
            text = stringResource(id = R.string.about_us),
            iconPainter = painterResource(id = R.drawable.ic_about_icon),
            iconSize = 22.dp
        ) {}
        Spacer(modifier = Modifier.height(20.dp))
        DrawerButton(
            text = stringResource(id = R.string.log_out),
            iconPainter = painterResource(id = R.drawable.ic_logout_icon),
            iconSize = 20.dp
        ) {}
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun DrawerButton(
    text: String,
    iconPainter: Painter,
    iconSize: Dp = 23.dp,
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
                painter = iconPainter,
                contentDescription = "",
                modifier = Modifier.size(iconSize),
                tint = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                modifier = Modifier.weight(1F),
                text = text,
                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onBackground)
            )
        }
    }
}

@Composable
private fun AdsLazyRow(viewModel: HomeViewModel, lazyRowState: LazyListState) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = lazyRowState,
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(11.dp),
    ) {
        items(viewModel.adsList) { ad ->
            AdItem(ad = ad)
        }
    }
}

@Composable
private fun AdsBulletsList(viewModel: HomeViewModel, lazyRowState: LazyListState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        for (index in viewModel.adsList.indices) {
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
                            MaterialTheme.colors.secondaryVariant
                    )
            )
        }
    }
}

@Composable
private fun AdItem(ad: Ad) {
    Box(
        modifier = Modifier
            .height(180.dp)
            .width(330.dp)
            .clip(RoundedCornerShape((7.dp)))
            .background(MaterialTheme.colors.surface)
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
private fun MostImportantRow() {
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
            name = stringResource(id = R.string.ekhraj_zakat),
            icon = painterResource(id = R.drawable.ic_zakat_icon)
        )
        CategoryItem(
            name = stringResource(id = R.string.kafalat_yateem),
            icon = painterResource(id = R.drawable.ic_yateem_icon)
        )
        CategoryItem(
            name = stringResource(id = R.string.ramadan_projects),
            icon = painterResource(id = R.drawable.ic_moon_icon)
        )
    }
}

@Composable
private fun ContributeWithUsRow() {
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
            iconSize = 25.dp
        )
        CategoryItem(
            name = stringResource(id = R.string.mosques),
            icon = painterResource(id = R.drawable.ic_mosque_icon),
            iconSize = 25.dp
        )
        CategoryItem(
            name = stringResource(id = R.string.health),
            icon = painterResource(id = R.drawable.ic_health_icon),
            iconSize = 22.dp,
        )
        CategoryItem(
            name = stringResource(id = R.string.education),
            icon = painterResource(id = R.drawable.ic_education_icon),
            iconSize = 28.dp
        )
    }
}

@Composable
private fun CategoryItem(
    name: String,
    icon: Painter,
    iconSize: Dp = 24.dp,
    categoryArg: String = ""
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
            .clickable(onClick = {
                // TODO: Navigate to Projects screen with categoryArg
            })
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