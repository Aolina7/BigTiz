package com.example.bigtiz.domain.usecase

import com.example.bigtiz.domain.model.TicketCategory
import com.example.bigtiz.domain.model.TicketOffer
import com.example.bigtiz.domain.repository.TicketRepository
import com.example.bigtiz.domain.repository.WalletRepository

class PurchaseTicketsUseCase(
    private val ticketRepository: TicketRepository,
    private val walletRepository: WalletRepository,
) {
    operator fun invoke(
        offers: List<TicketOffer>,
        selection: Map<TicketCategory, Int>,
        currentBalanceRub: Int,
    ): Boolean {
        val total = offers.sumOf { offer ->
            offer.priceRub * selection.getValueOrZero(offer.category)
        }

        val hasEnoughMoney = total in 1..currentBalanceRub
        val hasEnoughTickets = offers.all { offer ->
            selection.getValueOrZero(offer.category) <= offer.ticketsLeft
        }

        if (!hasEnoughMoney || !hasEnoughTickets) {
            return false
        }

        walletRepository.spend(total)
        ticketRepository.purchaseTickets(selection)
        return true
    }

    private fun Map<TicketCategory, Int>.getValueOrZero(category: TicketCategory): Int {
        return this[category] ?: 0
    }
}
