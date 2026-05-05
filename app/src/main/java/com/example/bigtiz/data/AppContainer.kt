package com.example.bigtiz.data

import com.example.bigtiz.data.repository.InMemoryRaceRepository
import com.example.bigtiz.data.repository.InMemoryTicketRepository
import com.example.bigtiz.data.repository.InMemoryWalletRepository
import com.example.bigtiz.domain.repository.RaceRepository
import com.example.bigtiz.domain.repository.TicketRepository
import com.example.bigtiz.domain.repository.WalletRepository
import com.example.bigtiz.domain.usecase.ObserveRaceInfoUseCase
import com.example.bigtiz.domain.usecase.ObserveTicketSelectionUseCase
import com.example.bigtiz.domain.usecase.PurchaseTicketsUseCase

class AppContainer {
    val raceRepository: RaceRepository = InMemoryRaceRepository()
    val ticketRepository: TicketRepository = InMemoryTicketRepository()
    val walletRepository: WalletRepository = InMemoryWalletRepository()

    val observeRaceInfoUseCase = ObserveRaceInfoUseCase(
        raceRepository = raceRepository,
        walletRepository = walletRepository,
    )

    val observeTicketSelectionUseCase = ObserveTicketSelectionUseCase(
        raceRepository = raceRepository,
        ticketRepository = ticketRepository,
        walletRepository = walletRepository,
    )

    val purchaseTicketsUseCase = PurchaseTicketsUseCase(
        ticketRepository = ticketRepository,
        walletRepository = walletRepository,
    )
}
