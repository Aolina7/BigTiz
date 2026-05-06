package com.example.bigtiz.domain.model

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
)