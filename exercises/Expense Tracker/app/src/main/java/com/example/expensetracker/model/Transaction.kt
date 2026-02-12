package com.example.expensetracker.model


data class Transaction(
    val id: Long = 0,
    val name: String,
    val amount: Double,
    val date: Long?,
    val category: Category?
)

