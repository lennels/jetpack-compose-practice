package com.example.campuspassmanager.ui

import androidx.lifecycle.ViewModel
import com.example.campuspassmanager.data.PassDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PassViewModel : ViewModel() {
    private val _passUiState = MutableStateFlow(PassUiState())
    val passUiState = _passUiState.asStateFlow()

    init {
        val passData = PassDataSource.samplePasses
        _passUiState.update { it.copy(passList = passData) }
    }

    fun topUpPass(passId: String) {
        _passUiState.update { currentState ->
            val updatedPass = currentState.passList.map { pass ->
                if (pass.id == passId) pass.copy(balance = pass.balance.plus(10)) else pass
            }
            currentState.copy(passList = updatedPass)
        }
    }

    fun deletePass(passId: String) {
        _passUiState.update { currentState ->
            val deletedPass = currentState.passList.filter { it.id != passId }
            currentState.copy(passList = deletedPass)
        }
    }
}