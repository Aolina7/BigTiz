package com.example.bigtiz.ui.screen.ticket_selection.domain

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Tickets(
    val FanZone: Int, val VipZone: Int, val PremiumZone: Int
)