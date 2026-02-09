package com.example.coffeeshoppos.ui

import android.view.Menu
import com.example.coffeeshoppos.model.MenuItem
import com.example.coffeeshoppos.model.OrderItem

data class ShopUiState(
    val menu: List<MenuItem> = emptyList(),
    val currentOrder: List<OrderItem> = emptyList()
) {
    val subtotal: Double
        get() = currentOrder.sumOf { it.item.price * it.quantity }
    val tax: Double get() = subtotal * 0.07
    val total: Double get() = subtotal + tax
    val canCheckout: Boolean get() = currentOrder.isNotEmpty()
}
