package com.example.bigtiz.ui.screen.pilot_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bigtiz.domain.model.Racer
import com.example.bigtiz.domain.usecase.GetAllRacersUseCase
import com.example.bigtiz.domain.usecase.GetRacerByIdUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PilotDetailsViewModel(
    private val getAllRacersUseCase: GetAllRacersUseCase,
    private val getRacerByIdUseCase: GetRacerByIdUseCase,
    private val initialRacerId: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow(PilotDetailsUiState())
    val uiState: StateFlow<PilotDetailsUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<ViewModelEvent>()
    val event = _event.asSharedFlow()

    init {
        loadAllRacers()
        loadRacerById(initialRacerId)
    }

    private fun loadAllRacers() {
        viewModelScope.launch {
            try {
                val racers = getAllRacersUseCase()
                _uiState.update { state ->
                    state.copy(allRacers = racers.toUiModelList())
                }
            } catch (e: Exception) {
                _event.emit(ViewModelEvent.ShowError("Ошибка загрузки списка: ${e.message}"))
            }
        }
    }

    private fun loadRacerById(racerId: Int) {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(isLoading = true) }
            try {
                val racer = getRacerByIdUseCase(racerId)
                _uiState.update { state ->
                    state.copy(
                        currentRacer = racer?.toUiModel(),
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update { state -> state.copy(isLoading = false) }
                _event.emit(ViewModelEvent.ShowError("Ошибка загрузки гонщика: ${e.message}"))
            }
        }
    }

    fun onMenuClick() {
        _uiState.update { state -> state.copy(isMenuVisible = true) }
    }

    fun onCloseMenu() {
        _uiState.update { state -> state.copy(isMenuVisible = false) }
    }

    fun onRacerClick(racer: RacerUiModel) {
        _uiState.update { state ->
            state.copy(
                currentRacer = racer,
                isMenuVisible = false
            )
        }
        loadRacerById(racer.id)
    }

    fun onNavigateToHome() {
        viewModelScope.launch {
            _event.emit(ViewModelEvent.NavigateToHome)
        }
    }

    fun onClearError() {
        _uiState.update { state -> state.copy(error = null) }
    }
}

sealed class ViewModelEvent {
    object NavigateToHome : ViewModelEvent()
    data class ShowError(val message: String) : ViewModelEvent()
}