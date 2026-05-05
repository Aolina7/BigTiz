package com.example.bigtiz.ui.screen.schedule_of_races.domain.usecase

import com.example.bigtiz.ui.screen.schedule_of_races.domain.model.Race
import com.example.bigtiz.ui.screen.schedule_of_races.domain.repository.ScheduleOfRacesRepository

class GetRacesUseCase(
    private val repository: ScheduleOfRacesRepository
) {
    operator fun invoke(): List<Race> {
        return repository.getRaces()
    }
}