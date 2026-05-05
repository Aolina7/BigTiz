package com.example.bigtiz.ui.screen.ticket_selection.domain

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@Serializable
@SuppressLint("UnsafeOptInUsageError")
data class Tickets(var FanZone: Int, var PremiumZone: Int, var VipZone: Int)