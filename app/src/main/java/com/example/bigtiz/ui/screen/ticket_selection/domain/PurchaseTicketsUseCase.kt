package com.example.bigtiz.ui.screen.ticket_selection.domain
class PurchaseTicketsUseCase(
    private val repository: TicketRepository
) {

    operator fun invoke(
        current: Tickets,
        fan: Int,
        vip: Int,
        prem: Int
    ): Tickets {

        val updated = current.copy(
            FanZone = current.FanZone - fan,
            VipZone = current.VipZone - vip,
            PremiumZone = current.PremiumZone - prem
        )

        repository.saveTickets(updated)

        return updated
    }
}