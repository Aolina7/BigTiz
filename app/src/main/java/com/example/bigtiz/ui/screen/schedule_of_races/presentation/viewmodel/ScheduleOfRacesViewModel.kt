package com.example.bigtiz.ui.screen.schedule_of_races.presentation.viewmodel

import androidx.compose.runtime.toMutableStateList
import com.example.bigtiz.ui.screen.schedule_of_races.domain.usecase.GetRacesUseCase
import com.example.bigtiz.ui.screen.schedule_of_races.presentation.mapper.toRaceUi


class ScheduleOfRacesViewModel (
    private val racesUseCase: GetRacesUseCase
) {
    val races = racesUseCase()
        .map { it.toRaceUi() }
        .toMutableStateList()
}