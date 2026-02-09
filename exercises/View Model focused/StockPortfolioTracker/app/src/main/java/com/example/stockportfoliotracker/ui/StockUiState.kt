package com.example.stockportfoliotracker.ui

import com.example.stockportfoliotracker.model.Stock

data class StockUiState(
    val portfolio: List<Stock> = emptyList()
) {
    val totalInvestment
        get() = portfolio.sumOf { it.sharesOwned * it.purchasePrice }
    val currentValue
        get() = portfolio.sumOf { it.sharesOwned * it.currentMarketPrice }
    val totalProfitLoss
        get() = currentValue - totalInvestment
    val isBullMarket
        get() = totalProfitLoss > 0
}
