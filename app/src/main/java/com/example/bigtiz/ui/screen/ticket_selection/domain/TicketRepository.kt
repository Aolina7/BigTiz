package com.example.bigtiz.ui.screen.ticket_selection.domain

interface TicketRepository {
    fun getTickets(): Tickets
    fun saveTickets(tickets: Tickets)
}