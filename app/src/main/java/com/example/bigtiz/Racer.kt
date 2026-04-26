package com.example.bigtiz

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.Composable

data class Racer(
    val id: Int,
    val name: String,
    val fullName: String,
    val imageResId: Int,
    val bio: String,
    val age: Int,
    val country: String,
    val wins: Int,
    val quote: String
) {
    val painter: Painter
        @Composable get() = painterResource(id = imageResId)

    val fullDescription: String
        get() = "$fullName - $country\nВозраст: $age лет\nПодиумов: $wins\n$quote\n\n$bio"
}