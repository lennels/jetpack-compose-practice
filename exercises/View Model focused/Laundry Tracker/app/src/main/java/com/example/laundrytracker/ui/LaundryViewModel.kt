package com.example.laundrytracker.ui

import androidx.lifecycle.ViewModel
import com.example.laundrytracker.data.LaundryDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LaundryViewModel(
) : ViewModel() {
    private val _laundryUiState = MutableStateFlow(LaundryUiState())
    val laundryUiState = _laundryUiState.asStateFlow()

    init {
        val data = LaundryDataSource().getEcoWash()
        _laundryUiState.update {
            it.copy(
                cycleName = data.baseName,
                baseMinutes = data.baseMinutes,
                extraRinseMinutes = data.extraRinseMinutes
            )
        }
    }

    fun addRinse() {
        _laundryUiState.update { currentState ->
            currentState.copy(
                extraRinseCount = currentState.extraRinseCount.inc()
            )
        }
    }

    fun removeRinse() {
        _laundryUiState.update { currentState ->
            if (currentState.extraRinseCount > 0)
            currentState.copy(
                extraRinseCount = currentState.extraRinseCount.dec()
            )
            else currentState
        }
    }

}