package com.example.mallsapp.ui

import androidx.lifecycle.ViewModel
import com.example.mallsapp.data.MallRepository
import com.example.mallsapp.model.Mall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MallViewModel : ViewModel() {
    private val _mallUiState = MutableStateFlow(MallUiState())
    val mallUiState = _mallUiState.asStateFlow()

    init {
        _mallUiState.update {
            it.copy(mallList = MallRepository.singaporeMalls)
        }
    }

    fun selectMall(mall: Mall) {
        _mallUiState.update { currentState ->
            currentState.copy(
                selectedMall = mall,
            )
        }
    }
}