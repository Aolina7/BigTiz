package com.example.bigtiz.domain.model

enum class TicketCategory {
    FAN_ZONE,
    VIP_ZONE,
    PREMIUM_ZONE,
}

data class TicketOffer(
    val category: TicketCategory,
    val title: String,
    val priceRub: Int,
    val ticketsLeft: Int,
)

data class Wallet(
    val balanceRub: Int,
)
