package com.example.laundrytracker.model

data class WashCycle(
    val baseName: String,
    val baseMinutes: Int,
    val extraRinseMinutes: Int
)