package com.example.bigtiz.presentation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import com.example.bigtiz.domain.model.TicketCategory
import com.example.bigtiz.domain.usecase.ObserveTicketSelectionUseCase
import com.example.bigtiz.domain.usecase.PurchaseTicketsUseCase
import com.example.bigtiz.presentation.mapper.toUiModel
import com.example.bigtiz.ui.screen.ticket_selection.TicketSelectionScreen

@Composable
fun TicketSelectionRoute(
    observeTicketSelectionUseCase: ObserveTicketSelectionUseCase,
    purchaseTicketsUseCase: PurchaseTicketsUseCase,
    onMenuClick: () -> Unit = {},
) {
    val ticketSelectionFlow = remember(observeTicketSelectionUseCase) { observeTicketSelectionUseCase() }
    val ticketSelectionData by ticketSelectionFlow.collectAsState(initial = null)
    val selection = remember { mutableStateMapOf<TicketCategory, Int>() }
    val data = ticketSelectionData ?: return

    TicketSelectionScreen(
        uiModel = data.toUiModel(selection),
        onMenuClick = onMenuClick,
        onDecrease = { category ->
            val current = selection[category] ?: 0
            if (current > 0) {
                selection[category] = current - 1
            }
        },
        onIncrease = { category ->
            val offer = data.ticketOffers.firstOrNull { it.category == category }
            if (offer != null) {
                val current = selection[category] ?: 0
                if (current < offer.ticketsLeft) {
                    selection[category] = current + 1
                }
            }
        },
        onPurchase = {
            val didPurchase = purchaseTicketsUseCase(
                offers = data.ticketOffers,
                selection = selection,
                currentBalanceRub = data.wallet.balanceRub,
            )

            if (didPurchase) {
                selection.clear()
            }
        },
    )
}
