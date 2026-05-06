package com.example.bigtiz.domain.usecase

import com.example.bigtiz.domain.model.Racer
import com.example.bigtiz.domain.repository.RacerRepository

class GetAllRacersUseCase(
    private val repository: RacerRepository
) {
    suspend operator fun invoke(): List<Racer> = repository.getAllRacers()
}