package com.example.gymtracker.model

data class Rank(
    val title: String,
    val minXP: Int,
    val xpMultiplier: Int // Higher ranks earn XP faster
)

class GymDataSource {
    fun getRankList(): List<Rank> {
        return listOf(
            Rank("Newbie", 0, 1),
            Rank("Regular", 20, 2),
            Rank("Athlete", 50, 5),
            Rank("Powerlifter", 100, 10)
        )
    }
}