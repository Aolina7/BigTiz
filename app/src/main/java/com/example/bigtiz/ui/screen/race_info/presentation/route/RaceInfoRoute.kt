package com.example.bigtiz.ui.screen.race_info.presentation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.bigtiz.ui.screen.race_info.domain.usecase.GetRaceInfoStaticUseCase
import com.example.bigtiz.ui.screen.race_info.presentation.mapper.toUiModel
import com.example.bigtiz.ui.screen.race_info.presentation.screen.RaceInfoScreen

@Composable
fun RaceInfoRoute(
    getRaceInfoUseCase: GetRaceInfoStaticUseCase,
    onMenuClick: () -> Unit = {},
    onBuyTicketClick: () -> Unit = {},
) {
    val raceInfoData = remember(getRaceInfoUseCase) { getRaceInfoUseCase() }

    val uiModel = raceInfoData.toUiModel()

    RaceInfoScreen(
        onMenuClick = onMenuClick,
        onBuyTicketClick = onBuyTicketClick,
    )
}