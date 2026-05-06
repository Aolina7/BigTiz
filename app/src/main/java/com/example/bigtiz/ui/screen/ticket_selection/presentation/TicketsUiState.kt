package com.example.bigtiz.ui.screen.ticket_selection.presentation

data class TicketsUiState(
    val fanSelected: Int = 0,
    val vipSelected: Int = 0,
    val premSelected: Int = 0,

    val fanAvailable: Int = 0,
    val vipAvailable: Int = 0,
    val premAvailable: Int = 0,

    val money: Int = 0
)