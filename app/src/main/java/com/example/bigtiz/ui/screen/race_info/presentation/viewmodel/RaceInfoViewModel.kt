package com.example.bigtiz.ui.screen.race_info.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bigtiz.ui.screen.race_info.domain.usecase.GetRaceInfoStaticUseCase
import com.example.bigtiz.ui.screen.race_info.presentation.mapper.toUiModel
import com.example.bigtiz.ui.screen.race_info.presentation.screen.RaceInfoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RaceInfoViewModel(
    private val getRaceInfoUseCase: GetRaceInfoStaticUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<RaceInfoUiState>(RaceInfoUiState.Loading)
    val uiState: StateFlow<RaceInfoUiState> = _uiState.asStateFlow()

    init {
        loadRaceInfo()
    }

    private fun loadRaceInfo() {
        viewModelScope.launch {
            // Имитация загрузки, если нужно
            val domainModel = getRaceInfoUseCase()
            _uiState.value = RaceInfoUiState.Success(domainModel.toUiModel())
        }
    }
}