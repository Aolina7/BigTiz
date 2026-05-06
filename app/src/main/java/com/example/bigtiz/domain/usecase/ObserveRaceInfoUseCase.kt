package com.example.bigtiz.domain.usecase

import com.example.bigtiz.domain.model.RaceDetails
import com.example.bigtiz.domain.model.Wallet
import com.example.bigtiz.domain.repository.RaceRepository
import com.example.bigtiz.domain.repository.WalletRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

data class RaceInfoData(
    val raceDetails: RaceDetails,
    val wallet: Wallet,
)

class ObserveRaceInfoUseCase(
    private val raceRepository: RaceRepository,
    private val walletRepository: WalletRepository,
) {
    operator fun invoke(): Flow<RaceInfoData> {
        return combine(
            raceRepository.observeRaceDetails(),
            walletRepository.observeWallet(),
        ) { raceDetails, wallet ->
            RaceInfoData(
                raceDetails = raceDetails,
                wallet = wallet,
            )
        }
    }
}
