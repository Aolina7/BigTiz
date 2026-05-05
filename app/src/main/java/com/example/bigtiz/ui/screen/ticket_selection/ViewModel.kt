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
    fun getVipZonePrice() : Int {
        return ticketUiState.VipZonePrice
    }
    fun getPremiumZonePrice() : Int {
        return ticketUiState.PremuimZonePrice
    }

    fun onIncreaseFan(viewModel: ViewModel) {
        viewModel.valueFan++
        viewModel.Total += viewModel.getFanZonePrice()
    }

    fun onDecreaseFan(viewModel: ViewModel) {
        viewModel.valueFan--
        viewModel.Total -= viewModel.getFanZonePrice()
    }

    fun onIncreaseVip(viewModel: ViewModel) {
        viewModel.valueVip++
        viewModel.Total += viewModel.getVipZonePrice()
    }

    fun onDecreaseVip(viewModel: ViewModel) {
        viewModel.valueVip--
        viewModel.Total -= viewModel.getVipZonePrice()
    }

    fun onIncreasePrem(viewModel: ViewModel) {
        viewModel.valuePrem++
        viewModel.Total += viewModel.getPremiumZonePrice()
    }

    fun onDecreasePrem(viewModel: ViewModel) {
        viewModel.valuePrem--
        viewModel.Total -= viewModel.getPremiumZonePrice()
    }


}