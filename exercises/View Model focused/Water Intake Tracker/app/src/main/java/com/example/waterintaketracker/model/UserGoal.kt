package com.example.waterintaketracker.model

data class UserGoal(
    val userName: String,
    val dailyGoalGlasses: Int
)

// Data Source
class WaterDataSource {
    fun getUserGoal() = UserGoal(userName = "Student", dailyGoalGlasses = 8)
}