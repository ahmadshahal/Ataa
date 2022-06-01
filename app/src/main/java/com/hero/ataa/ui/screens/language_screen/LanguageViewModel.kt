package com.hero.ataa.ui.screens.language_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hero.ataa.shared.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    val isArabic = mutableStateOf<Boolean>(savedStateHandle.get<Boolean>(Constants.NavArgs.IS_ARABIC_KEY)!!)
}