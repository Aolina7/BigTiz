package com.example.bigtiz.ui.screen.race_info.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bigtiz.ui.screen.race_info.domain.usecase.GetRaceInfoStaticUseCase

class RaceInfoViewModelFactory(
    private val getRaceInfoUseCase: GetRaceInfoStaticUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RaceInfoViewModel::class.java)) {
            return RaceInfoViewModel(getRaceInfoUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}