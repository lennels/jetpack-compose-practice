package com.example.stockportfoliotracker.data

import com.example.stockportfoliotracker.model.Stock

object StockDataSource {
    val stockList = listOf(
        Stock("GOOGL", 10, 2500.0, 2800.0),
        Stock("AAPL", 50, 150.0, 145.0),
        Stock("TSLA", 5, 700.0, 900.0)
    )
}