package com.example.bigtiz.domain.usecase

import com.example.bigtiz.domain.model.Race
import com.example.bigtiz.domain.model.TicketOffer
import com.example.bigtiz.domain.model.Wallet
import com.example.bigtiz.domain.repository.RaceRepository
import com.example.bigtiz.domain.repository.TicketRepository
import com.example.bigtiz.domain.repository.WalletRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

data class TicketSelectionData(
    val race: Race,
    val ticketOffers: List<TicketOffer>,
    val wallet: Wallet,
)

class ObserveTicketSelectionUseCase(
    private val raceRepository: RaceRepository,
    private val ticketRepository: TicketRepository,
    private val walletRepository: WalletRepository,
) {
    operator fun invoke(): Flow<TicketSelectionData> {
        return combine(
            raceRepository.observeRaceDetails(),
            ticketRepository.observeTicketOffers(),
            walletRepository.observeWallet(),
        ) { raceDetails, ticketOffers, wallet ->
            TicketSelectionData(
                race = raceDetails.race,
                ticketOffers = ticketOffers,
                wallet = wallet,
            )
        }
    }
}
