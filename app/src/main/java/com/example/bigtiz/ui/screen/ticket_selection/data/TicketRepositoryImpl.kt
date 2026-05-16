package com.example.bigtiz.ui.screen.ticket_selection.data

import com.example.bigtiz.ui.screen.ticket_selection.domain.TicketRepository
import com.example.bigtiz.ui.screen.ticket_selection.domain.Tickets
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
class TicketRepositoryImpl(
    private val file: File
) : TicketRepository {

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    override fun getTickets(): Tickets {
        val text = file.readText()
        return json.decodeFromString(text)
    }

    override fun saveTickets(tickets: Tickets) {
        file.writeText(json.encodeToString(tickets))
    }
}