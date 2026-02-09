package com.example.librarymemberportal.model

data class Membership(
    val tierName: String,
    val maxBooksAllowed: Int,
    val finePerLateDay: Int
)
