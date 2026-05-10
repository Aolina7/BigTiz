package com.example.bigtiz.ui.screen.schedule_of_races.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bigtiz.ui.screen.schedule_of_races.data.ScheduleOfRacesRepositoryImpl
import com.example.bigtiz.ui.screen.schedule_of_races.domain.usecase.GetRacesUseCase

class ScheduleOfRacesViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScheduleOfRacesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ScheduleOfRacesViewModel(
                racesUseCase = GetRacesUseCase(ScheduleOfRacesRepositoryImpl())
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}