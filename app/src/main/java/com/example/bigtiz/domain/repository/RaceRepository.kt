package com.example.bigtiz.domain.repository

import com.example.bigtiz.domain.model.RaceDetails
import kotlinx.coroutines.flow.StateFlow

interface RaceRepository {
    fun observeRaceDetails(): StateFlow<RaceDetails>
}
