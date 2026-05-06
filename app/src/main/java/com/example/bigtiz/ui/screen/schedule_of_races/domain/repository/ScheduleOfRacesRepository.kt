package com.example.bigtiz.ui.screen.schedule_of_races.domain.repository

import com.example.bigtiz.ui.screen.schedule_of_races.domain.model.Race

interface ScheduleOfRacesRepository {
    fun getRaces() : List<Race>
}