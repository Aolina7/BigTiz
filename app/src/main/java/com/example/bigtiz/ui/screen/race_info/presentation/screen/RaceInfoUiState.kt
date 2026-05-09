package com.example.bigtiz.ui.screen.race_info.presentation.screen

import com.example.bigtiz.ui.screen.race_info.presentation.model.RaceInfoUiModel

sealed class RaceInfoUiState {
    object Loading : RaceInfoUiState()
    data class Success(val uiModel: RaceInfoUiModel) : RaceInfoUiState()
    data class Error(val message: String) : RaceInfoUiState()
}