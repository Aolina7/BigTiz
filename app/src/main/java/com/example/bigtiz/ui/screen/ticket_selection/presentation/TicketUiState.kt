package com.example.bigtiz.ui.screen.ticket_selection.presentation

data class TicketUiState(

    val fanAvailable: Int = 0,
    val vipAvailable: Int = 0,
    val premiumAvailable: Int = 0,

    val fanSelected: Int = 0,
    val vipSelected: Int = 0,
    val premiumSelected: Int = 0,

    val money: Int = 10000
)