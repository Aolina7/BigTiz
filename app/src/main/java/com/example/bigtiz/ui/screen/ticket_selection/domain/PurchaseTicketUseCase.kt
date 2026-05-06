package com.example.bigtiz.ui.screen.ticket_selection.domain

class PurchaseTicketUseCase(
    private val repository: TicketRepository
) {
    operator fun invoke(
        tickets: Tickets,
        fan: Int,
        vip: Int,
        prem: Int
    ): Tickets {

        if (fan > tickets.FanZone ||
            vip > tickets.VipZone ||
            prem > tickets.PremiumZone
        ) {
            throw IllegalStateException("Not enough tickets")
        }

        val updated = tickets.copy(
            FanZone = tickets.FanZone - fan,
            VipZone = tickets.VipZone - vip,
            PremiumZone = tickets.PremiumZone - prem
        )

        repository.saveTickets(updated)
        return updated
    }
}