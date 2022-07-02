package com.hero.ataa.ui.screens.project_screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.hero.ataa.R
import com.hero.ataa.domain.models.Project
import com.hero.ataa.shared.Constants
import com.hero.ataa.ui.components.*
import com.hero.ataa.utils.MoneyTransformation
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProjectScreen(project: Project, navController: NavController) {

    val scrollState = rememberScrollState()
    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetContent = { BottomSheetContent() },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
        sheetBackgroundColor = MaterialTheme.colors.background,
    ) {
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
                DonateButton(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    modalBottomSheetState = modalBottomSheetState
                )
            }
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

@Composable
private fun ContentColumn(scrollState: ScrollState, project: Project) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .height(170.dp)
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DonateButton(modifier: Modifier = Modifier, modalBottomSheetState: ModalBottomSheetState) {

    val scope = rememberCoroutineScope()

    Box(modifier = modifier.padding(bottom = 16.dp)) {
        MaterialButton(
            onClicked = {
                scope.launch {
                    modalBottomSheetState.show()
                }
            },
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
private fun TagsRow(project: Project) {
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

@Composable
private fun BottomSheetContent() {
    val chosenAmountIdx = remember {
        mutableStateOf(0)
    }
    val amount = remember {
        mutableStateOf("10000")
    }
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = stringResource(id = R.string.choose_the_amount_you_want_to_donate),
            style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onBackground),
        )
        Spacer(modifier = Modifier.height(20.dp))
        TitledTextField(
            title = stringResource(id = R.string.optional_amount),
            value = amount.value, onValueChanged = {
                if (it.length <= Constants.MAX_MONEY_DONATION) {
                    amount.value = it
                    chosenAmountIdx.value = -1
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            visualTransformation = MoneyTransformation(stringResource(id = R.string.syrian_pounds))
        )
        Spacer(modifier = Modifier.height(20.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSpacing = 10.dp,
            crossAxisSpacing = 15.dp,
            mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
            crossAxisAlignment = FlowCrossAxisAlignment.Center
        ) {
            SquaredRadioButton(
                text = stringResource(id = R.string._10k),
                isSelected = chosenAmountIdx.value == 0,
                onClick = {
                    chosenAmountIdx.value = 0
                    amount.value = "10000"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._25k),
                isSelected = chosenAmountIdx.value == 1,
                onClick = {
                    chosenAmountIdx.value = 1
                    amount.value = "25000"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._50k),
                isSelected = chosenAmountIdx.value == 2,
                onClick = {
                    chosenAmountIdx.value = 2
                    amount.value = "50000"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._100k),
                isSelected = chosenAmountIdx.value == 3,
                onClick = {
                    chosenAmountIdx.value = 3
                    amount.value = "100000"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._250k),
                isSelected = chosenAmountIdx.value == 4,
                onClick = {
                    chosenAmountIdx.value = 4
                    amount.value = "250000"
                }
            )
            SquaredRadioButton(
                text = stringResource(id = R.string._500k),
                isSelected = chosenAmountIdx.value == 5,
                onClick = {
                    chosenAmountIdx.value = 5
                    amount.value = "500000"
                }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        MaterialButton(
            onClicked = {
                // TODO.
            },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            content = {
                Text(
                    text = stringResource(id = R.string.next),
                    style = MaterialTheme.typography.button
                )
            }
        )
    }
}