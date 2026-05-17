package com.example.bigtiz.ui.screen.purchase_success.presentation

import com.example.bigtiz.ui.screen.purchase_success.domain.PurchaseInfo

data class PurchaseSuccessUiState(
    val purchaseInfo: PurchaseInfo = PurchaseInfo(
        fanTickets = 0,
        vipTickets = 0,
        premiumTickets = 0,
        totalPaid = 0
    )
)