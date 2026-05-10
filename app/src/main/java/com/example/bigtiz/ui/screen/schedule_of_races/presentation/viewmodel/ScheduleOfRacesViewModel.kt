package com.example.bigtiz.ui.screen.schedule_of_races.presentation.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.bigtiz.ui.screen.schedule_of_races.domain.usecase.GetRacesUseCase
import com.example.bigtiz.ui.screen.schedule_of_races.presentation.mapper.toRaceUi


class ScheduleOfRacesViewModel (
    private val racesUseCase: GetRacesUseCase
) : ViewModel() {
    val races = racesUseCase()
        .map { it.toRaceUi() }
        .toMutableStateList()
}