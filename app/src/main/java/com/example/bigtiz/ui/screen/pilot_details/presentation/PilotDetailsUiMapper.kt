package com.example.bigtiz.ui.screen.presentation

import com.example.bigtiz.domain.model.Racer

data class RacerUiModel(
    val id: Int,
    val name: String,
    val fullName: String,
    val imageResId: Int,
    val bio: String,
    val age: Int,
    val country: String,
    val wins: Int,
    val quote: String,
    val formattedAge: String,
    val formattedWins: String,
    val formattedQuote: String
)

object PilotDetailsUiMapper {
    fun toUiModel(racer: Racer): RacerUiModel {
        return RacerUiModel(
            id = racer.id,
            name = racer.name,
            fullName = racer.fullName,
            imageResId = racer.imageResId,
            bio = racer.bio,
            age = racer.age,
            country = racer.country,
            wins = racer.wins,
            quote = racer.quote,
            formattedAge = "${racer.age} лет",
            formattedWins = "${racer.wins} ${getWinsSuffix(racer.wins)}",
            formattedQuote = "«${racer.quote}»"
        )
    }

    fun toUiModelList(racers: List<Racer>): List<RacerUiModel> {
        return racers.map { toUiModel(it) }
    }

    private fun getWinsSuffix(wins: Int): String {
        return when {
            wins % 10 == 1 && wins % 100 != 11 -> "подиум"
            wins % 10 in 2..4 && (wins % 100 < 10 || wins % 100 >= 20) -> "подиума"
            else -> "подиумов"
        }
    }
}