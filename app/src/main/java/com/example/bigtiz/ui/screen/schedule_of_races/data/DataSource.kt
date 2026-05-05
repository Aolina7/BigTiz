package com.example.bigtiz.ui.screen.schedule_of_races.data

import com.example.bigtiz.ui.screen.schedule_of_races.domain.model.Race
import java.time.LocalDate

object DataSource {
    fun getRaceList(): List<Race> {
        return listOf(
            Race(LocalDate.of(2026, 3, 4), "Австралия"),
            Race(LocalDate.of(2026, 4, 5), "Германия"),
            Race(LocalDate.of(2026, 5, 4), "Турция"),
        )
    }
}