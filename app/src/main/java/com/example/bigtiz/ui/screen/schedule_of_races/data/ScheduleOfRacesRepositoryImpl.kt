package com.example.bigtiz.ui.screen.schedule_of_races.data

import com.example.bigtiz.ui.screen.schedule_of_races.domain.model.Race
import com.example.bigtiz.ui.screen.schedule_of_races.domain.repository.ScheduleOfRacesRepository
import java.time.LocalDate

class ScheduleOfRacesRepositoryImpl : ScheduleOfRacesRepository {
    override fun getRaces(): List<Race> {
        return DataSource.getRaceList()
    }
}