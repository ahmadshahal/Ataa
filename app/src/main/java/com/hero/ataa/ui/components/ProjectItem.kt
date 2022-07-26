package com.hero.ataa.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hero.ataa.R
import com.hero.ataa.domain.models.Project
import com.hero.ataa.shared.Constants
import com.hero.ataa.ui.navigation.Screen
import com.skydoves.landscapist.coil.CoilImage
import java.util.*

@Composable
fun ProjectItem(project: Project, navController: NavController) {
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