package com.example.mallsapp.model

data class Mall(
    val id: Int,
    val imgRes: Int,
    val name: String,
    val region: Region,
    val description: String
)

enum class Region {
    CENTRAL, NORTH_EAST, WEST
}