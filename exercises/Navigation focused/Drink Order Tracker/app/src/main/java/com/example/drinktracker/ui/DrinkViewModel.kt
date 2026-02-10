package com.example.drinktracker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinktracker.data.DrinkData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DrinkViewModel : ViewModel() {
    private val _drinkUiModel = MutableStateFlow(DrinkUiState())
    val drinkUiModel = _drinkUiModel.asStateFlow()

    init {
        loadOrder()
    }

    private fun loadOrder() {
        viewModelScope.launch {
            delay(2000)
            val data = DrinkData.availableCoffee
            _drinkUiModel.update { currentState ->
                currentState.copy(
                    isLoading = false,
                    orderDetails = data
                )
            }
        }
    }

    fun upgradeOrder(id: String) {
        _drinkUiModel.update { currentState ->
            val upgradedList = currentState.orderDetails.map { drink ->
                if (drink.id == id && drink.size != "Large") drink.copy(
                    size = "Large",
                    price = drink.price.plus(1.50)
                ) else drink
            }
            currentState.copy(
                orderDetails = upgradedList
            )
        }
    }
}