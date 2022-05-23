package com.hero.ataa.ui.screens.project_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.hero.ataa.R
import com.hero.ataa.domain.models.Project
import com.hero.ataa.ui.components.AppBar
import com.hero.ataa.ui.components.MaterialButton
import com.hero.ataa.ui.components.ProgressBar
import com.hero.ataa.ui.components.Tag
import com.skydoves.landscapist.coil.CoilImage
import java.util.*

@Composable
fun ProjectScreen(project: Project, navController: NavController) {
    val scrollState = rememberScrollState()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            ProjectAppBar(navController = navController)
        },
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            ContentColumn(project = project, scrollState = scrollState)
            DonateButton(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun ProjectAppBar(navController: NavController) {
    AppBar(
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ContentColumn(scrollState: ScrollState, project: Project) {
    CompositionLocalProvider(
        LocalOverScrollConfiguration provides null,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            CoilImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .clip(RoundedCornerShape(7.dp)),
                imageModel = project.imageUrl,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = project.title,
                style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground),
                lineHeight = 25.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            ProgressBar(progress = project.progress, height = 7.dp)
            Spacer(modifier = Modifier.height(5.dp))
            ProgressRow(project = project)
            Text(
                text = "${stringResource(R.string.raised)} ${
                    "%,d".format(
                        Locale.US,
                        project.raised
                    )
                } ${stringResource(R.string.currency)}",
                style = MaterialTheme.typography.overline.copy(color = MaterialTheme.colors.onBackground),
            )
            Spacer(modifier = Modifier.height(15.dp))
            TagsRow(project = project)
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = stringResource(id = R.string.project_description),
                style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground),
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = project.description,
                style = MaterialTheme.typography.overline.copy(color = MaterialTheme.colors.primaryVariant),
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.project_goals),
                style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground),
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = project.goals,
                style = MaterialTheme.typography.overline.copy(color = MaterialTheme.colors.primaryVariant),
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.height(75.dp))
        }
    }
}

@Composable
private fun DonateButton(modifier: Modifier = Modifier) {
    Box(modifier = modifier.padding(bottom = 16.dp)) {
        MaterialButton(
            onClicked = { /*TODO*/ },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            content = {
                Text(
                    text = stringResource(id = R.string.donate_now),
                    style = MaterialTheme.typography.button
                )
            }
        )
    }
}

@Composable
private fun ProgressRow(project: Project) {
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
            style = MaterialTheme.typography.overline.copy(color = MaterialTheme.colors.onBackground),
        )
        Text(
            text = project.progress.toString() + "%",
            style = MaterialTheme.typography.overline.copy(color = MaterialTheme.colors.primary),
        )
    }
}

@Composable
fun TagsRow(project: Project) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth(),
        mainAxisSpacing = 10.dp,
        crossAxisSpacing = 10.dp,
        mainAxisAlignment = FlowMainAxisAlignment.End
    ) {
        project.tags.forEach { tag ->
            Tag(title = tag)
        }
        Tag(title = project.location)
    }
}
