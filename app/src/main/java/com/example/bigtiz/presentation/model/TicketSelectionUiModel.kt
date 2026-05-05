package com.example.bigtiz.presentation.model

import com.example.bigtiz.domain.model.TicketCategory

data class TicketSelectionUiModel(
    val raceTitle: String,
    val raceLocation: String,
    val balanceRub: Int,
    val totalRub: Int,
    val zones: List<TicketZoneUiModel>,
    val canPurchase: Boolean,
)

data class TicketZoneUiModel(
    val category: TicketCategory,
    val title: String,
    val priceRub: Int,
    val ticketsLeft: Int,
    val selectedCount: Int,
)
