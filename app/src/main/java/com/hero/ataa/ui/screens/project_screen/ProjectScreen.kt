package com.hero.ataa.ui.screens.project_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.hero.ataa.R
import com.hero.ataa.domain.models.Project
import com.hero.ataa.shared.Constants
import com.hero.ataa.ui.components.*
import com.hero.ataa.ui.navigation.Screen
import com.hero.ataa.utils.MoneyTransformation
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProjectScreen(
    project: Project,
    navController: NavController,
    viewModel: ProjectViewModel = hiltViewModel()
) {

    val scrollState = rememberScrollState()
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    ModalBottomSheetLayout(
        sheetContent = { BottomSheetContent(navController = navController, project = project) },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
        sheetBackgroundColor = MaterialTheme.colors.background,
    ) {
        Scaffold(
            backgroundColor = MaterialTheme.colors.background,
            topBar = {
                ProjectAppBar(navController = navController, scrollState = scrollState)
            },
            contentColor = MaterialTheme.colors.onBackground,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                ContentColumn(project = project, scrollState = scrollState, viewModel = viewModel)
                if(viewModel.userLoggedInFlow.collectAsState().value) {
                    DonateButton(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        modalBottomSheetState = modalBottomSheetState
                    )
                }
            }
        }
    }
}

@Composable
private fun ProjectAppBar(navController: NavController, scrollState: ScrollState) {
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
        },
        elevation = if (scrollState.value > 0) 1.dp else 0.dp
    )
}

@Composable
private fun ContentColumn(scrollState: ScrollState, project: Project, viewModel: ProjectViewModel) {
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
        Spacer(modifier = Modifier.height(16.dp))
        if(viewModel.userLoggedInFlow.collectAsState().value) {
            Spacer(modifier = Modifier.height(59.dp))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DonateButton(
    modifier: Modifier = Modifier,
    modalBottomSheetState: ModalBottomSheetState
) {

    val scope = rememberCoroutineScope()

    Box(modifier = modifier.padding(bottom = 16.dp)) {
        MaterialButton(
            onClick = {
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
    val language = Locale.getDefault().language
    FlowRow(
        modifier = Modifier
            .fillMaxWidth(),
        mainAxisSpacing = 10.dp,
        crossAxisSpacing = 10.dp,
        mainAxisAlignment = if (language == "ar") FlowMainAxisAlignment.End else FlowMainAxisAlignment.Start
    ) {
        project.tags.forEach { tag ->
            Tag(title = tag)
        }
        Tag(title = project.location)
    }
}

@Composable
private fun BottomSheetContent(navController: NavController, project: Project) {
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
            onClick = {
                navController.navigate(Screen.PaymentScreen.route + "/${(amount.value.toIntOrNull() ?: 0)}/${project.id}")
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