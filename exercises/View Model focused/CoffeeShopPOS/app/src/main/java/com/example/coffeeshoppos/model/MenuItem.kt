package com.example.coffeeshoppos.model

data class MenuItem(
    val name: String,
    val price: Double,
    val category: String // "Drink" or "Food"
)