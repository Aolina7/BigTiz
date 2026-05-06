package com.example.bigtiz.domain.usecase

import com.example.bigtiz.domain.model.Racer
import com.example.bigtiz.domain.repository.RacerRepository

class GetRacerByIdUseCase(
    private val repository: RacerRepository
) {
    suspend operator fun invoke(id: Int): Racer? = repository.getRacerById(id)
}