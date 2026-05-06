package com.example.bigtiz.data.repository

import com.example.bigtiz.data.datasource.LocalRacerDataSource
import com.example.bigtiz.domain.model.Racer
import com.example.bigtiz.domain.repository.RacerRepository

class RacerRepositoryImpl(
    private val localDataSource: LocalRacerDataSource
) : RacerRepository {

    override suspend fun getAllRacers(): List<Racer> {
        return localDataSource.getAllRacers()
    }

    override suspend fun getRacerById(id: Int): Racer? {
        return localDataSource.getRacerById(id)
    }

    override suspend fun getRacerByName(name: String): Racer? {
        return localDataSource.getRacerByName(name)
    }
}