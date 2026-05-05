package com.example.bigtiz.domain.repository

import com.example.bigtiz.domain.model.Racer

interface RacerRepository {
    suspend fun getAllRacers(): List<Racer>
    suspend fun getRacerById(id: Int): Racer?
    suspend fun getRacerByName(name: String): Racer?
}