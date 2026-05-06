package com.example.bigtiz.domain.repository

import com.example.bigtiz.domain.model.Wallet
import kotlinx.coroutines.flow.StateFlow

interface WalletRepository {
    fun observeWallet(): StateFlow<Wallet>

    fun spend(amountRub: Int)
}
