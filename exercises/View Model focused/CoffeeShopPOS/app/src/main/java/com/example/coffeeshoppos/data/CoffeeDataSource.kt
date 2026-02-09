package com.example.coffeeshoppos.data

import com.example.coffeeshoppos.model.MenuItem

class CoffeeDataSource {
    fun getMenu(): List<MenuItem> = listOf(
        MenuItem("Espresso", 3.50, "Drink"),
        MenuItem("Latte", 4.50, "Drink"),
        MenuItem("Croissant", 3.00, "Food"),
        MenuItem("Muffin", 3.50, "Food")
    )
}