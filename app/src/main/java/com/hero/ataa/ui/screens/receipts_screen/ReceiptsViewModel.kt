package com.hero.ataa.ui.screens.receipts_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.domain.use_cases.GetReceiptsUseCase
import com.hero.ataa.shared.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReceiptsViewModel @Inject constructor(
    private val getReceiptsUseCase: GetReceiptsUseCase
) : ViewModel() {

    private val _uiState = mutableStateOf<ReceiptsUiState>(ReceiptsUiState.Loading)
    val uiState: State<ReceiptsUiState>
        get() = _uiState

    init {
        getReceipts()
    }

    fun getReceipts() {
        viewModelScope.launch {
            getReceiptsUseCase().collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        _uiState.value = ReceiptsUiState.Loading
                    }
                    is DataState.Error -> {
                        _uiState.value = ReceiptsUiState.Error
                    }
                    is DataState.Success -> {
                        _uiState.value = ReceiptsUiState.Success(receipts = dataState.data)
                    }
                    else -> Unit
                }
            }
        }
    }
}