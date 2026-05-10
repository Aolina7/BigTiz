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

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    private var allRacers: List<Racer> = emptyList()

    init {
        loadAllRacers()
        loadRacerById(initialRacerId)
    }

    private fun loadAllRacers() {
        viewModelScope.launch {
            try {
                allRacers = getAllRacersUseCase()
                _uiState.update { state ->
                    state.copy(allRacers = allRacers.toUiModelList())
                }
            } catch (e: Exception) {
                _uiState.update { state ->
                    state.copy(error = "Ошибка загрузки списка гонщиков: ${e.message}")
                }
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
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        error = "Ошибка загрузки данных гонщика: ${e.message}"
                    )
                }
            }
        }
    }

    fun onEvent(event: PilotDetailsEvent) {
        when (event) {
            is PilotDetailsEvent.SelectRacer -> {
                selectRacer(event.racer)
            }
            is PilotDetailsEvent.ToggleMenu -> {
                toggleMenu()
            }
            is PilotDetailsEvent.HideMenu -> {
                hideMenu()
            }
            is PilotDetailsEvent.NavigateToHome -> {
                navigateToHome()
            }
        }
    }

    private fun selectRacer(racer: RacerUiModel) {
        _uiState.update { state ->
            state.copy(
                currentRacer = racer,
                isMenuVisible = false
            )
        }
    }

    private fun toggleMenu() {
        _uiState.update { state ->
            state.copy(isMenuVisible = !state.isMenuVisible)
        }
    }

    private fun hideMenu() {
        _uiState.update { state ->
            state.copy(isMenuVisible = false)
        }
    }

    private fun navigateToHome() {
        viewModelScope.launch {
            _navigationEvent.emit(NavigationEvent.NavigateToHome)
        }
    }
}

sealed class PilotDetailsEvent {
    data class SelectRacer(val racer: RacerUiModel) : PilotDetailsEvent()
    object ToggleMenu : PilotDetailsEvent()
    object HideMenu : PilotDetailsEvent()
    object NavigateToHome : PilotDetailsEvent()
}

sealed class NavigationEvent {
    object NavigateToHome : NavigationEvent()
}