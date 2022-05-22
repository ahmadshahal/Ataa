package com.hero.ataa.ui.screens.home_screen

import com.hero.ataa.domain.models.Ad

sealed class AdsUiState {
    object Loading : AdsUiState()
    data class Success(val adsList: List<Ad>) : AdsUiState()
}
