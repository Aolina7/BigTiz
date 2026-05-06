package com.example.bigtiz.ui.screen.pilot_details.presentation

import com.example.bigtiz.domain.model.Racer

data class PilotDetailsUiState(
    val currentRacer: RacerUiModel? = null,
    val isLoading: Boolean = false,
    val isMenuVisible: Boolean = false,
    val error: String? = null
)

data class RacerUiModel(
    val id: Int,
    val name: String,
    val fullName: String,
    val imageResId: Int,
    val bio: String,
    val age: Int,
    val country: String,
    val wins: Int,
    val quote: String
) {
    val formattedAge: String
        get() = "$age лет"

    val formattedWins: String
        get() = "$wins подиумов"

    val formattedQuote: String
        get() = "«$quote»"
}

fun Racer.toUiModel(): RacerUiModel {
    return RacerUiModel(
        id = id,
        name = name,
        fullName = fullName,
        imageResId = imageResId,
        bio = bio,
        age = age,
        country = country,
        wins = wins,
        quote = quote
    )
}

fun List<Racer>.toUiModelList(): List<RacerUiModel> {
    return this.map { it.toUiModel() }
}