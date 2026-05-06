package com.example.bigtiz.ui.screen.ticket_selection.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.bigtiz.ui.screen.ticket_selection.domain.PurchaseTicketUseCase
import com.example.bigtiz.ui.screen.ticket_selection.domain.TicketRepository
import com.example.bigtiz.ui.screen.ticket_selection.domain.Tickets
import com.example.bigtiz.ui.screen.ticket_selection.presentation.TicketsUiState

class TicketViewModel(
    private val purchaseUseCase: PurchaseTicketUseCase,
    private val repository: TicketRepository
) {

    var uiState by mutableStateOf(
        TicketsUiState(
            fanAvailable = 0,
            vipAvailable = 0,
            premAvailable = 0,
            money = 1000
        )
    )
        private set

    init {
        loadTickets()
    }

    var tickets by mutableStateOf(repository.getTickets())
        private set

    fun getFanPrice() = 100
    fun getVipPrice() = 200
    fun getPremPrice() = 300

    private fun loadTickets() {
        val tickets = repository.getTickets()

        uiState = uiState.copy(
            fanAvailable = tickets.FanZone,
            vipAvailable = tickets.VipZone,
            premAvailable = tickets.PremiumZone
        )
    }

    val total: Int
        get() = uiState.fanSelected * getFanPrice() +
                uiState.vipSelected * getVipPrice() +
                uiState.premSelected * getPremPrice()


    fun increaseFan() {
        if (uiState.fanSelected < uiState.fanAvailable) {
            uiState = uiState.copy(
                fanSelected = uiState.fanSelected + 1
            )
        }
    }

    fun decreaseFan() {
        if (uiState.fanSelected > 0) {
            uiState = uiState.copy(
                fanSelected = uiState.fanSelected - 1
            )
        }
    }

    fun increaseVip() {
        if (uiState.vipSelected < uiState.vipAvailable) {
            uiState = uiState.copy(
                vipSelected = uiState.vipSelected + 1
            )
        }
    }

    fun decreaseVip() {
        if (uiState.vipSelected > 0) {
            uiState = uiState.copy(
                vipSelected = uiState.vipSelected - 1
            )
        }
    }

    fun increasePrem() {
        if (uiState.premSelected < uiState.premAvailable) {
            uiState = uiState.copy(
                premSelected = uiState.premSelected + 1
            )
        }
    }

    fun decreasePrem() {
        if (uiState.premSelected > 0) {
            uiState = uiState.copy(
                premSelected = uiState.premSelected - 1
            )
        }
    }

    fun purchase() {
        if (total > uiState.money) return

        val currentTickets = Tickets(
            FanZone = uiState.fanAvailable,
            VipZone = uiState.vipAvailable,
            PremiumZone = uiState.premAvailable
        )

        val updated = purchaseUseCase(
            currentTickets,
            uiState.fanSelected,
            uiState.vipSelected,
            uiState.premSelected
        )

        uiState = uiState.copy(
            fanAvailable = updated.FanZone,
            vipAvailable = updated.VipZone,
            premAvailable = updated.PremiumZone,
            money = uiState.money - total,
            fanSelected = 0,
            vipSelected = 0,
            premSelected = 0
        )
    }
}