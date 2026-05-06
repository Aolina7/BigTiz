package com.example.bigtiz.data.repository

import com.example.bigtiz.R
import com.example.bigtiz.domain.model.Pilot
import com.example.bigtiz.domain.model.Race
import com.example.bigtiz.domain.model.RaceDetails
import com.example.bigtiz.domain.model.RaceResult
import com.example.bigtiz.domain.model.Team
import com.example.bigtiz.domain.repository.RaceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class InMemoryRaceRepository : RaceRepository {
    private val raceDetails = MutableStateFlow(
        RaceDetails(
            race = Race(
                id = "big-tiz",
                title = "Бик Тиз",
                location = "Australia",
                imageResId = R.drawable.team,
            ),
            teams = listOf(
                Team(id = "meteor", name = "Meteor Racing"),
                Team(id = "thunder", name = "Thunder GP"),
            ),
            pilots = listOf(
                Pilot(id = "diana", name = "Диана", teamId = "meteor"),
                Pilot(id = "anita", name = "Анита", teamId = "meteor"),
                Pilot(id = "alexander", name = "Александр", teamId = "thunder"),
                Pilot(id = "alina", name = "Алина", teamId = "thunder"),
            ),
            results = listOf(
                RaceResult(pilotId = "diana", position = 1, timeDelta = "+13.722S", points = 18),
                RaceResult(pilotId = "anita", position = 2, timeDelta = "+15.27S", points = 15),
                RaceResult(pilotId = "alexander", position = 3, timeDelta = "+15.754S", points = 12),
                RaceResult(pilotId = "alina", position = 4, timeDelta = "+23.479S", points = 10),
            ),
        ),
    )

    override fun observeRaceDetails(): StateFlow<RaceDetails> = raceDetails
}
