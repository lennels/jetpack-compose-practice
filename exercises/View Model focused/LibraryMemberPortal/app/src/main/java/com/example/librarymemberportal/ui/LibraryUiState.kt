package com.example.librarymemberportal.ui

import com.example.librarymemberportal.data.LibraryDataSource
import com.example.librarymemberportal.model.Membership

data class LibraryUiState(
    val booksBorrowed: Int = 0,
    val daysLate: Int = 0,
    val membership: Membership? = null
) {
    val canBorrowMore: Boolean
        get() =
            booksBorrowed < (membership?.maxBooksAllowed ?: 0)

    val currentFine: Int get() = daysLate * (membership?.finePerLateDay ?: 0)
    val isGoodStanding: Boolean get() = daysLate <= 0
}
