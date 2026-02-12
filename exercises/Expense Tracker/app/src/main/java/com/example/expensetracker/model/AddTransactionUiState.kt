package com.example.expensetracker.model

data class AddTransactionUiState(
    val id: Long = 0,
    val name: String = "",
    val amountInput: String = "",
    val amount: Double = 0.0,
    val selectedDate: Long? = null,
    val showDateModal: Boolean = false,
    val category: Category? = null,
    val categoryDropdownMenuSelected: Boolean = false,
    val nameError: String? = null,
    val amountError: String? = null,
    val categoryError: String? = null,
    val dateError: String? = null,
    val onSuccessSubmission: Boolean = false
)
