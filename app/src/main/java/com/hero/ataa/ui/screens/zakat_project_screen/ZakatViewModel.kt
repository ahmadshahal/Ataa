package com.hero.ataa.ui.screens.zakat_project_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ZakatViewModel @Inject constructor() : ViewModel() {
    val chosenAmountIdx = mutableStateOf(0)
    val amount = mutableStateOf("10000")
}