package com.example.bigtiz.ui.screen.race_info.presentation.mapper

import com.example.bigtiz.ui.screen.race_info.domain.model.RaceInfoStatic
import com.example.bigtiz.ui.screen.race_info.presentation.model.RaceInfoUiModel
import com.example.bigtiz.ui.screen.race_info.presentation.model.RaceResultRowUiModel


fun RaceInfoStatic.toUiModel(): RaceInfoUiModel =
    RaceInfoUiModel(
        title = title,
        balanceRub = balanceRub,
        imageResId = imageResId,
        resultRows = resultRows.map { row ->
            RaceResultRowUiModel(
                position = row.position,
                pilotName = row.pilotName,
                timeDelta = row.timeDelta,
                points = row.points,
            )
        },
    )