package com.hero.ataa.ui.screens.miskeen_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MiskeenViewModel @Inject constructor() : ViewModel() {
    val number = mutableStateOf("1")
    val chosenNumberIdx = mutableStateOf(0)
}