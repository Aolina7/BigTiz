package com.example.bigtiz.ui.screen.ticket_selection.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.example.bigtiz.ui.common.AppConstants
import com.example.bigtiz.ui.screen.ticket_selection.TicketUiState

import androidx.compose.runtime.*
import com.example.bigtiz.ui.screen.ticket_selection.domain.PurchaseTicketUseCase
import com.example.bigtiz.ui.screen.ticket_selection.domain.TicketRepository

class TicketViewModel(
    private val purchaseUseCase: PurchaseTicketUseCase,
    private val repository: TicketRepository
) {

    var tickets by mutableStateOf(repository.getTickets())
        private set

    var valueFan by mutableIntStateOf(0)
    var valueVip by mutableIntStateOf(0)
    var valuePrem by mutableIntStateOf(0)

    var money by mutableIntStateOf(1000)

    fun getFanPrice() = 100
    fun getVipPrice() = 200
    fun getPremPrice() = 300

    val total: Int
        get() = valueFan * getFanPrice() +
                valueVip * getVipPrice() +
                valuePrem * getPremPrice()

    fun increaseFan() { valueFan++ }
    fun decreaseFan() { if (valueFan > 0) valueFan-- }

    fun increaseVip() { valueVip++ }
    fun decreaseVip() { if (valueVip > 0) valueVip-- }

    fun increasePrem() { valuePrem++ }
    fun decreasePrem() { if (valuePrem > 0) valuePrem-- }

    fun purchase() {
        if (total > money) return

        val updated = purchaseUseCase.execute(
            tickets,
            valueFan,
            valueVip,
            valuePrem
        )

        tickets = updated
        money -= total

        valueFan = 0
        valueVip = 0
        valuePrem = 0
    }
}