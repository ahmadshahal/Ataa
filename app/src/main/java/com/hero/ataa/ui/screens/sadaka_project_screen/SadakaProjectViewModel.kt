package com.hero.ataa.ui.screens.sadaka_project_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SadakaProjectViewModel @Inject constructor() : ViewModel() {
    val moneyFieldText = mutableStateOf("10000")
    val chosenAmountIdx = mutableStateOf(0)
}