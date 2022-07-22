package com.hero.ataa.ui.screens.sadaka_project_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SadakaViewModel @Inject constructor() : ViewModel() {
    val chosenAmountIdx = mutableStateOf(0)
    val amount = mutableStateOf("10000")
}