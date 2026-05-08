package com.example.bigtiz.ui.screen.ticket_selection.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.bigtiz.ui.common.AppConstants
import com.example.bigtiz.ui.screen.ticket_selection.domain.PurchaseTicketsUseCase
import com.example.bigtiz.ui.screen.ticket_selection.domain.TicketPrices
import com.example.bigtiz.ui.screen.ticket_selection.domain.TicketRepository
import com.example.bigtiz.ui.screen.ticket_selection.domain.Tickets

class TicketViewModel(
    private val repository: TicketRepository,
    private val purchaseUseCase: PurchaseTicketsUseCase
) {

    var uiState by mutableStateOf(TicketUiState())
        private set

    init {
        loadTickets()
    }

    private fun loadTickets() {

        val tickets = repository.getTickets()

        uiState = uiState.copy(
            fanAvailable = tickets.FanZone,
            vipAvailable = tickets.VipZone,
            premiumAvailable = tickets.PremiumZone,
            money = AppConstants.balance
        )
    }

    val total: Int
        get() =
            uiState.fanSelected * TicketPrices.FAN +
                    uiState.vipSelected * TicketPrices.VIP +
                    uiState.premiumSelected * TicketPrices.PREMIUM

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

    fun increasePremium() {

        if (uiState.premiumSelected < uiState.premiumAvailable) {

            uiState = uiState.copy(
                premiumSelected = uiState.premiumSelected + 1
            )
        }
    }

    fun decreasePremium() {

        if (uiState.premiumSelected > 0) {

            uiState = uiState.copy(
                premiumSelected = uiState.premiumSelected - 1
            )
        }
    }

    fun purchase() {

        if (total > uiState.money) return

        val currentTickets = Tickets(
            FanZone = uiState.fanAvailable,
            VipZone = uiState.vipAvailable,
            PremiumZone = uiState.premiumAvailable
        )

        val updated = purchaseUseCase(
            currentTickets,
            uiState.fanSelected,
            uiState.vipSelected,
            uiState.premiumSelected
        )

        AppConstants.balance -= total

        uiState = uiState.copy(
            fanAvailable = updated.FanZone,
            vipAvailable = updated.VipZone,
            premiumAvailable = updated.PremiumZone,

            fanSelected = 0,
            vipSelected = 0,
            premiumSelected = 0,

            money = AppConstants.balance
        )
    }
}