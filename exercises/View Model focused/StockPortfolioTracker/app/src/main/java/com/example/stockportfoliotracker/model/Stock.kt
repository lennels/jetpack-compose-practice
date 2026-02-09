package com.example.stockportfoliotracker.model

data class Stock(
    val ticker: String,
    val sharesOwned: Int,
    val purchasePrice: Double,
    val currentMarketPrice: Double
)