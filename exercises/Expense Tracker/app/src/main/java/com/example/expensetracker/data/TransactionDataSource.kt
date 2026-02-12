package com.example.expensetracker.data

import com.example.expensetracker.model.Category
import com.example.expensetracker.model.Transaction

object TransactionDataSource {
    val transactionData = listOf(
        Transaction(
            id = 1L,
            name = "Burger",
            amount = 30.0,
            date = 20489L, // 2026-02-05
            category = Category.FOOD
        ),
        Transaction(
            id = 2L,
            name = "Intro to DevOps",
            amount = 25.30,
            date = 20488L, // 2026-02-04
            category = Category.BOOKS
        ),
        Transaction(
            id = 3L,
            name = "Pizza",
            amount = 30.0,
            date = 20487L, // 2026-02-03
            category = Category.FOOD
        ),
        Transaction(
            id = 4L,
            name = "Kotlin: An Illustrated Guide",
            amount = 22.0,
            date = 20485L, // 2026-02-01
            category = Category.BOOKS
        ),
        Transaction(
            id = 5L,
            name = "Monthly Topup",
            amount = 80.0,
            date = 20481L, // 2026-01-28
            category = Category.TRANSPORT
        ),
        Transaction(
            id = 6L,
            name = "Covid",
            amount = 70.50,
            date = 20478L, // 2026-01-25
            category = Category.HEALTH
        ),
    )
}