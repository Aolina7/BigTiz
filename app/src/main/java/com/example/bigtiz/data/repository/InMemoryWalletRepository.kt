package com.example.bigtiz.data.repository

import com.example.bigtiz.domain.model.Wallet
import com.example.bigtiz.domain.repository.WalletRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class InMemoryWalletRepository : WalletRepository {
    private val wallet = MutableStateFlow(Wallet(balanceRub = 10_000))

    override fun observeWallet(): StateFlow<Wallet> = wallet

    override fun spend(amountRub: Int) {
        wallet.value = wallet.value.copy(balanceRub = wallet.value.balanceRub - amountRub)
    }
}
