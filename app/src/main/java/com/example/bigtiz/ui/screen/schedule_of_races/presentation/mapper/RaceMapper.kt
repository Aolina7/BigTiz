package com.example.bigtiz.ui.screen.schedule_of_races.presentation.mapper

import com.example.bigtiz.ui.screen.schedule_of_races.domain.model.Race
import com.example.bigtiz.ui.screen.schedule_of_races.presentation.ui.RaceUi
import java.time.format.DateTimeFormatter

fun Race.toRaceUi(): RaceUi {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val fullDate = date.format(formatter)
    val parts = fullDate.split(".")

    return RaceUi(
        dayMonth = "${parts[0]}.${parts[1]}",
        year = parts[2],
        country = country
    )
}