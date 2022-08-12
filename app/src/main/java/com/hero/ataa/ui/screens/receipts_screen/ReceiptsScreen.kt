package com.hero.ataa.ui.screens.receipts_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.hero.ataa.R
import com.hero.ataa.domain.models.Receipt
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.ui.components.*
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ReceiptsScreen(
    navController: NavController,
    viewModel: ReceiptsViewModel = hiltViewModel(),
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
            ReceiptsAppBar(navController = navController, scrollState = lazyListState)
        },
        snackbarHost = { state ->
            SnackbarHost(state) { data ->
                Snackbar(
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = MaterialTheme.colors.onSecondary,
                    snackbarData = data,
                )
            }
        },
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        when (val uiState = viewModel.uiState.value) {
            is ReceiptsUiState.Success -> {
                if(uiState.receipts.isEmpty()) {
                    NoResultsWidget(painter = painterResource(id = R.drawable.ic_no_data))
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
                            uiState.receipts
                        ) { receipt ->
                            ReceiptItem(receipt = receipt)
                        }
                    }
                }
            }
            is ReceiptsUiState.Loading -> {
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
            is ReceiptsUiState.Error -> {
                ErrorWidget(onClick = { viewModel.getReceipts() })
            }
        }
    }
}

@Composable
private fun ReceiptsAppBar(navController: NavController, scrollState: LazyListState) {
    AppBar(
        title = {
            Text(
                text = stringResource(id = R.string.donations_receipts),
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
private fun ReceiptItem(receipt: Receipt) {
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
            .padding(vertical = 16.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            if(receipt.title.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = receipt.title,
                    style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onSurface),
                    lineHeight = 22.sp,
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            val language = Locale.getDefault().language
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                mainAxisSpacing = 10.dp,
                crossAxisSpacing = 10.dp,
                mainAxisAlignment = if (language == "ar") FlowMainAxisAlignment.End else FlowMainAxisAlignment.Start
            ) {
                receipt.tags.forEach { tag ->
                    Tag(title = tag)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            InfoRow(prefix = stringResource(id = R.string.project_id), suffix = receipt.projectId)
            Spacer(modifier = Modifier.height(10.dp))
            InfoRow(
                prefix = stringResource(id = R.string.donation_date),
                // TODO.
                suffix = receipt.donationDate
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.8.dp)
                    .background(MaterialTheme.colors.primaryVariant.copy(0.2F))
            )
            Spacer(modifier = Modifier.height(16.dp))
            InfoRow(
                prefix = stringResource(id = R.string.donation_value),
                suffix = "${
                    "%,d".format(
                        Locale.US,
                        receipt.donationValue
                    )
                } ${stringResource(R.string.currency)}",
            )
            Spacer(modifier = Modifier.height(10.dp))
            InfoRow(
                prefix = stringResource(id = R.string.payment_method),
                suffix = "Paypal"
            )
        }
    }
}

@Composable
private fun InfoRow(
    prefix: String,
    suffix: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = prefix,
            style = MaterialTheme.typography.overline.copy(color = MaterialTheme.colors.onSurface),
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = suffix,
            style = MaterialTheme.typography.overline.copy(color = MaterialTheme.colors.primaryVariant),
        )
    }
}