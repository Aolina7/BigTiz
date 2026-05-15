package com.example.bigtiz.ui.screen.purchase_success.domain

data class PurchaseInfo(
    val fanTickets: Int,
    val vipTickets: Int,
    val premiumTickets: Int,
    val totalPaid: Int
) {

    val totalTickets: Int
        get() = fanTickets + vipTickets + premiumTickets
}