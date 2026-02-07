package com.example.makansplitter.data

data class MakanItem(
    val id: Int,
    val name: String,
    val price: Double,
    val quantity: Int = 0
)
