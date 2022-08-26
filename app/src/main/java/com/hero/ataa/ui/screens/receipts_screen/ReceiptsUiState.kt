package com.hero.ataa.ui.screens.receipts_screen

import com.hero.ataa.domain.models.Receipt

sealed class ReceiptsUiState {
    data class Success(val receipts: List<Receipt>) : ReceiptsUiState()
    object Loading : ReceiptsUiState()
    object Error : ReceiptsUiState()
}
