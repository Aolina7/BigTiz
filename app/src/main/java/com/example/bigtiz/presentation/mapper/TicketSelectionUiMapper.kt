package com.example.bigtiz.presentation.mapper

import com.example.bigtiz.domain.model.TicketCategory
import com.example.bigtiz.domain.usecase.TicketSelectionData
import com.example.bigtiz.presentation.model.TicketSelectionUiModel
import com.example.bigtiz.presentation.model.TicketZoneUiModel

fun TicketSelectionData.toUiModel(
    selection: Map<TicketCategory, Int>,
): TicketSelectionUiModel {
    val totalRub = ticketOffers.sumOf { offer ->
        offer.priceRub * (selection[offer.category] ?: 0)
    }

    return TicketSelectionUiModel(
        raceTitle = race.title,
        raceLocation = race.location,
        balanceRub = wallet.balanceRub,
        totalRub = totalRub,
        zones = ticketOffers.map { offer ->
            TicketZoneUiModel(
                category = offer.category,
                title = offer.title,
                priceRub = offer.priceRub,
                ticketsLeft = offer.ticketsLeft,
                selectedCount = selection[offer.category] ?: 0,
            )
        },
        canPurchase = totalRub in 1..wallet.balanceRub,
    )
}
