package com.example.laundrytracker.ui

data class LaundryUiState(
    val cycleName: String = "",
    val baseMinutes: Int = 0,
    val extraRinseMinutes: Int = 0,
    val extraRinseCount: Int = 0,
) {
    val totalTime: Int get() = baseMinutes + (extraRinseCount * extraRinseMinutes)
}
