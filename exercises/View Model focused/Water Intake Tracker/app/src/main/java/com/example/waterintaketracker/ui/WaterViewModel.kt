package com.example.waterintaketracker.ui

import androidx.lifecycle.ViewModel
import com.example.waterintaketracker.model.UserGoal
import com.example.waterintaketracker.model.WaterDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WaterViewModel : ViewModel() {
    private val _waterUiState = MutableStateFlow(WaterUiState())
    val waterUiState = _waterUiState.asStateFlow()

    init {
        val data = WaterDataSource().getUserGoal()
        _waterUiState.update {
            it.copy(
                userName = data.userName,
                goal = data.dailyGoalGlasses
            )
        }
    }

    fun drinkGlass() {
        _waterUiState.update { currentState->
            currentState.copy(
                currentAmount = currentState.currentAmount.inc()
            )
        }
    }

    fun reset() {
        _waterUiState.update { currentState ->
            currentState.copy(
                currentAmount = 0
            )
        }
    }
}