package com.example.campuspassmanager.model

data class CampusPass(
    val id: String,
    val title: String,
    val holderName: String,
    val balance: Double,
    val color: Long // Use Color(0xFF...) hex values
)