package com.example.bigtiz.ui.screen.race_info.domain.model

import androidx.annotation.DrawableRes

data class RaceInfoStatic(
    val title: String,
    val balanceRub: Int,
    @DrawableRes val imageResId: Int,
    val resultRows: List<RaceResultRowStatic>,
)

data class RaceResultRowStatic(
    val position: Int,
    val pilotName: String,
    val timeDelta: String,
    val points: Int,
)

