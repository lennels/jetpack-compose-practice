package com.example.drinktracker.ui

import com.example.drinktracker.model.Drink

// File: DrinkUiState.kt
data class DrinkUiState(
    val orderDetails: List<Drink> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)