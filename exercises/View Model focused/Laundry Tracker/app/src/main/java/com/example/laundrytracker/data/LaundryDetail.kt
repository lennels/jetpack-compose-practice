package com.example.laundrytracker.data

import com.example.laundrytracker.model.WashCycle

class LaundryDataSource {
    fun getEcoWash(): WashCycle {
        return WashCycle(
            baseName = "Eco-Friendly Wash",
            baseMinutes = 45,
            extraRinseMinutes = 10
        )
    }
}