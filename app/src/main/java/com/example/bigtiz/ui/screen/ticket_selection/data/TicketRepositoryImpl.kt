package com.example.bigtiz.ui.screen.ticket_selection.data

import android.annotation.SuppressLint
import com.example.bigtiz.ui.screen.ticket_selection.domain.TicketRepository
import com.example.bigtiz.ui.screen.ticket_selection.domain.Tickets
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import java.io.File



@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class TicketsDto(
    val FanZone: Int,
    val VipZone: Int,
    val PremiumZone: Int
)
class TicketRepositoryImpl(
    private val file: File
) : TicketRepository {

    override fun getTickets(): Tickets {
        if (!file.exists()) return Tickets(0, 0, 0)

        val json = file.readText()
        val dto = Json.decodeFromString<TicketsDto>(json)

        return Tickets(dto.FanZone, dto.VipZone, dto.PremiumZone)
    }

    override fun saveTickets(tickets: Tickets) {
        val dto = TicketsDto(
            tickets.FanZone,
            tickets.VipZone,
            tickets.PremiumZone
        )
        val json = Json.encodeToString(dto)
        file.writeText(json)
    }
}