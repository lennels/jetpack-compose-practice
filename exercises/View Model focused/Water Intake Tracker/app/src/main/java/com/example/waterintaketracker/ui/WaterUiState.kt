package com.example.waterintaketracker.ui

data class WaterUiState(
    val userName: String = "",
    val goal: Int = 0,
    val currentAmount: Int = 0
) {
    val glassRemaining: Int get() = (goal - currentAmount).coerceAtLeast(0)
    val statusMessage: String
        get() = if (currentAmount < goal) "Keep drinking!"
        else "Goal Reached!"
}
