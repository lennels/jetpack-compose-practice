package com.example.librarymemberportal.ui

import androidx.lifecycle.ViewModel
import com.example.librarymemberportal.data.LibraryDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LibraryViewModel : ViewModel() {
    private val _libraryUiState = MutableStateFlow(LibraryUiState())
    val libraryUiState = _libraryUiState.asStateFlow()

    init {
        val data = LibraryDataSource.membershipData
        _libraryUiState.update {
            it.copy(
                membership = data
            )
        }
    }

    fun borrowBook() {
        _libraryUiState.update { currentState ->
            if (currentState.canBorrowMore)
                currentState.copy(
                    booksBorrowed = currentState.booksBorrowed.inc()
                ) else {
                currentState
            }
        }
    }

    fun returnBook() {
        _libraryUiState.update { currentState ->
            if (currentState.booksBorrowed > 0)
                currentState.copy(
                    booksBorrowed = currentState.booksBorrowed.dec()
                )
            else
                currentState
        }
    }

    fun incrementLateDays() {
        _libraryUiState.update { currentState ->
            currentState.copy(
                daysLate = currentState.daysLate.inc()
            )
        }
    }
}