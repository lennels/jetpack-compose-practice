package com.example.gymtracker.ui

import androidx.lifecycle.ViewModel
import com.example.gymtracker.model.GymDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GymViewModel : ViewModel() {
    private val _gymUiState = MutableStateFlow(GymUiState())
    val gymUiState = _gymUiState.asStateFlow()
    val rankList = GymDataSource().getRankList()

    fun completeWorkout() {
        _gymUiState.update { currentState ->
            val newXp = currentState.xpMultiplier + currentState.totalXp

            val activeRank = rankList.findLast { it.minXP <= newXp } ?: rankList.first()
            currentState.copy(
                totalWorkouts = currentState.totalWorkouts.inc(),
                currentRankTitle = activeRank.title,
                totalXp = newXp,
                xpMultiplier = activeRank.xpMultiplier
            )
        }
    }
}