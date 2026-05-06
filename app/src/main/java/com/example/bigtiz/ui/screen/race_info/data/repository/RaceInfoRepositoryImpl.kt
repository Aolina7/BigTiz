package com.example.bigtiz.ui.screen.race_info.data.repository

import com.example.bigtiz.ui.screen.race_info.data.dataSource.RaceInfoDataSource
import com.example.bigtiz.ui.screen.race_info.domain.model.RaceInfoStatic
import com.example.bigtiz.ui.screen.race_info.domain.repository.RaceInfoStaticRepository

class RaceInfoRepositoryImpl : RaceInfoStaticRepository {
    override fun getRaceInfo(): RaceInfoStatic = RaceInfoDataSource.raceInfo
}