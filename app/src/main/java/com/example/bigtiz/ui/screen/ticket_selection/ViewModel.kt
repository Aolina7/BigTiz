package com.example.bigtiz.ui.screen.ticket_selection

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.example.bigtiz.ui.common.AppConstants

class ViewModel {
    var Total : Int by mutableIntStateOf(0)
    var money : Int by mutableIntStateOf(AppConstants.balance)
    var valuePrem by mutableIntStateOf(0)
    var valueFan by mutableIntStateOf(0)
    var valueVip by mutableIntStateOf(0)

    val ticketUiState : TicketUiState = TicketUiState()
    fun getFanZonePrice() : Int {
        return ticketUiState.FanZonePrice
    }
    fun geVipZonePrice() : Int {
        return ticketUiState.VipZonePrice
    }
    fun getPremiumZonePrice() : Int {
        return ticketUiState.PremuimZonePrice
    }

}