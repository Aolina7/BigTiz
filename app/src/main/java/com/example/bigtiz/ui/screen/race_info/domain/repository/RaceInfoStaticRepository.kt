package com.example.bigtiz.ui.screen.race_info.domain.repository

import com.example.bigtiz.ui.screen.race_info.domain.model.RaceInfoStatic

interface RaceInfoStaticRepository {
    fun getRaceInfo(): RaceInfoStatic
}