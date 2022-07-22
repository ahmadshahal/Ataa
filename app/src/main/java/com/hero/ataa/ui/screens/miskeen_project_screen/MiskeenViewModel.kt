package com.hero.ataa.ui.screens.miskeen_project_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.domain.use_cases.GetMiskeenValueUseCase
import com.hero.ataa.shared.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MiskeenViewModel @Inject constructor(
    private val getMiskeenValueUseCase: GetMiskeenValueUseCase
) : ViewModel() {
    val number = mutableStateOf("1")
    val chosenNumberIdx = mutableStateOf(0)

    private val _uiState = mutableStateOf<MiskeenUiState>(MiskeenUiState.Loading)
    val uiState: State<MiskeenUiState>
        get() = _uiState

    init {
        getMiskeenValue()
    }

    fun getMiskeenValue() {
        viewModelScope.launch {
            getMiskeenValueUseCase().collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        _uiState.value = MiskeenUiState.Loading
                    }
                    is DataState.Error -> {
                        _uiState.value = MiskeenUiState.Error
                    }
                    is DataState.Success -> {
                        _uiState.value = MiskeenUiState.Success(miskeenValue = dataState.data)
                    }
                    else -> Unit
                }
            }
        }
    }
}