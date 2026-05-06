package com.example.bigtiz.domain.model

import androidx.annotation.DrawableRes

data class Team(
    val id: String,
    val name: String,
)

data class Pilot(
    val id: String,
    val name: String,
    val teamId: String,
)

data class Race(
    val id: String,
    val title: String,
    val location: String,
    @DrawableRes val imageResId: Int,
)

data class RaceResult(
    val pilotId: String,
    val position: Int,
    val timeDelta: String,
    val points: Int,
)

data class RaceDetails(
    val race: Race,
    val teams: List<Team>,
    val pilots: List<Pilot>,
    val results: List<RaceResult>,
)
