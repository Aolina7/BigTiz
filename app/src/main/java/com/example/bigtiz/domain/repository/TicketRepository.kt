package com.example.bigtiz.domain.repository

import com.example.bigtiz.domain.model.TicketCategory
import com.example.bigtiz.domain.model.TicketOffer
import kotlinx.coroutines.flow.StateFlow

interface TicketRepository {
    fun observeTicketOffers(): StateFlow<List<TicketOffer>>

    fun purchaseTickets(selection: Map<TicketCategory, Int>)
}
