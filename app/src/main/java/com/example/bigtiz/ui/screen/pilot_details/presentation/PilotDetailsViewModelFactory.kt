package com.example.bigtiz.ui.screen.pilot_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bigtiz.data.datasource.LocalRacerDataSource
import com.example.bigtiz.data.repository.RacerRepositoryImpl
import com.example.bigtiz.domain.usecase.GetAllRacersUseCase
import com.example.bigtiz.domain.usecase.GetRacerByIdUseCase

class PilotDetailsViewModelFactory(
    private val racerId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PilotDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PilotDetailsViewModel(
                getAllRacersUseCase = GetAllRacersUseCase(
                    RacerRepositoryImpl(LocalRacerDataSource())
                ),
                getRacerByIdUseCase = GetRacerByIdUseCase(
                    RacerRepositoryImpl(LocalRacerDataSource())
                ),
                initialRacerId = racerId
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}