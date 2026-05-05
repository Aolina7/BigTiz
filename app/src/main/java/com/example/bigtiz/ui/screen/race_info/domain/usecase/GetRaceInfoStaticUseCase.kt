package com.example.bigtiz.ui.screen.race_info.domain.usecase

import com.example.bigtiz.ui.screen.race_info.domain.model.RaceInfoStatic
import com.example.bigtiz.ui.screen.race_info.domain.repository.RaceInfoStaticRepository

class GetRaceInfoStaticUseCase(
    private val repository: RaceInfoStaticRepository,
) {
    operator fun invoke(): RaceInfoStatic = repository.getRaceInfo()
}

