package com.example.bigtiz.ui.screen.purchase_success.ViewModel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bigtiz.ui.screen.purchase_success.domain.PurchaseInfo
import com.example.bigtiz.ui.screen.purchase_success.presentation.PurchaseSuccessUiState

class PurchaseSuccessViewModel : ViewModel() {

    public var uiState by mutableStateOf(PurchaseSuccessUiState())
        private set

    fun setPurchaseInfo(
        fan: Int,
        vip: Int,
        premium: Int,
        total: Int
    ) {

        uiState = uiState.copy(
            purchaseInfo = PurchaseInfo(
                fanTickets = fan,
                vipTickets = vip,
                premiumTickets = premium,
                totalPaid = total
            )
        )
    }
}