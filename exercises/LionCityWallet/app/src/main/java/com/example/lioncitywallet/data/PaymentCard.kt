package com.example.lioncitywallet.data

import androidx.compose.ui.graphics.Color

data class PaymentCard(
    val cardType: String,
    val lastFour: String,
    val color: Color
)
