package com.hero.ataa.ui.screens.sacrifice_project_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.domain.use_cases.GetSacrificeValueUseCase
import com.hero.ataa.shared.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SacrificeViewModel @Inject constructor(
    private val getSacrificeValueUseCase: GetSacrificeValueUseCase
) : ViewModel() {
    val number = mutableStateOf("1")
    val chosenNumberIdx = mutableStateOf(0)

    private val _uiState = mutableStateOf<SacrificeUiState>(SacrificeUiState.Loading)
    val uiState: State<SacrificeUiState>
        get() = _uiState

    init {
        getSacrificeValue()
    }

    private fun getSacrificeValue() {
        viewModelScope.launch {
            getSacrificeValueUseCase().collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        _uiState.value = SacrificeUiState.Loading
                    }
                    is DataState.Error -> {
                        _uiState.value = SacrificeUiState.Error
                    }
                    is DataState.Success -> {
                        _uiState.value = SacrificeUiState.Success(sacrificeValue = dataState.data)
                    }
                    else -> Unit
                }
            }
        }
    }
}