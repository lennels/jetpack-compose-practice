package com.example.lioncitywallet.data

data class Transaction(
    val merchant: String,
    val amount: Double,
    val category: String, // e.g., "Transport", "Food"
    val paymentMethod: PaymentCard
)
