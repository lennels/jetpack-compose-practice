package com.example.stockportfoliotracker.ui

import androidx.lifecycle.ViewModel
import com.example.stockportfoliotracker.data.StockDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StockViewModel : ViewModel() {
    private val _stockUiState = MutableStateFlow(StockUiState())
    val stockUiState = _stockUiState.asStateFlow()

    private val stockData = StockDataSource.stockList

    init {
        _stockUiState.update {
            it.copy(portfolio = stockData)
        }
    }

    fun updateMarketPrice(ticker: String, newPrice: Double) {
        _stockUiState.update { currentState ->
            val updatedMarketPrice = currentState.portfolio.map { stock ->
                if (stock.ticker == ticker) stock.copy(currentMarketPrice = newPrice) else stock
            }
            currentState.copy(
                portfolio = updatedMarketPrice
            )
        }
    }

    fun sellAll(ticker: String) {
        _stockUiState.update { currentState ->
            val soldList = currentState.portfolio.filter { it.ticker != ticker }
            currentState.copy(
                portfolio = soldList
            )
        }
    }

    fun splitStock(ticker: String) {
        _stockUiState.update { currentState ->
            val updatedStock = currentState.portfolio.map { stock ->
                if (stock.ticker == ticker) stock.copy(
                    sharesOwned = stock.sharesOwned * 2,
                    purchasePrice = stock.purchasePrice / 2,
                    currentMarketPrice = stock.currentMarketPrice / 2
                ) else stock
            }
            currentState.copy(portfolio = updatedStock)
        }
    }
}