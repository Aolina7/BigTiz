package com.example.bigtiz.ui.screen.race_info.data.dataSource

import com.example.bigtiz.R
import com.example.bigtiz.ui.screen.race_info.domain.model.RaceInfoStatic
import com.example.bigtiz.ui.screen.race_info.domain.model.RaceResultRowStatic

object RaceInfoDataSource {
    val raceInfo: RaceInfoStatic =
        RaceInfoStatic(
            title = "Бик Тиз",
            balanceRub = 0,
            imageResId = R.drawable.team,
            resultRows = listOf(
                RaceResultRowStatic(
                    position = 1,
                    pilotName = "Диана",
                    timeDelta = "+13.722S",
                    points = 18
                ),
                RaceResultRowStatic(
                    position = 2,
                    pilotName = "Анита",
                    timeDelta = "+15.27S",
                    points = 15
                ),
                RaceResultRowStatic(
                    position = 3,
                    pilotName = "Александр",
                    timeDelta = "+15.754S",
                    points = 12
                ),
                RaceResultRowStatic(
                    position = 4,
                    pilotName = "Алина",
                    timeDelta = "+23.479S",
                    points = 10
                ),
            ),
        )
}