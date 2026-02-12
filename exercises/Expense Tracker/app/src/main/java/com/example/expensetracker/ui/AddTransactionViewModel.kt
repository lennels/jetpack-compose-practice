package com.example.expensetracker.ui

import androidx.lifecycle.ViewModel
import com.example.expensetracker.model.AddTransactionUiState
import com.example.expensetracker.model.Category
import com.example.expensetracker.model.Transaction
import com.example.expensetracker.model.TransactionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddTransactionViewModel : ViewModel() {
    private val _addTransactionUiState = MutableStateFlow(AddTransactionUiState())
    val addTransactionUiState = _addTransactionUiState.asStateFlow()
    private val _transactionUiState = MutableStateFlow(TransactionUiState())
    fun nameTextbox(string: String) {
        _addTransactionUiState.update { currentState ->
            currentState.copy(name = string)
        }
    }

    fun amountTextbox(amount: String) {
        val isValidInput =
            amount.isEmpty() || amount.all { it.isDigit() || it == '.' } && amount.count { it == '.' } <= 1
        if (isValidInput) {
            _addTransactionUiState.update { currentState ->
                currentState.copy(
                    amount = amount.toDoubleOrNull() ?: 0.0,
                    amountInput = amount
                )
            }
        }
    }

    fun activateCategoryDropBox() {
        _addTransactionUiState.update { currentState ->
            currentState.copy(
                categoryDropdownMenuSelected = !currentState.categoryDropdownMenuSelected
            )
        }
    }

    fun dismissCategoryDropBox() {
        _addTransactionUiState.update { currentState ->
            currentState.copy(
                categoryDropdownMenuSelected = false
            )
        }
    }

    fun selectCategory(category: Category) {
        _addTransactionUiState.update { currentState ->
            if (currentState.category != category) currentState.copy(category = category) else currentState
        }
        dismissCategoryDropBox()
    }

    fun showModal() {
        _addTransactionUiState.update { currentState ->
            currentState.copy(
                showDateModal = true
            )
        }
    }

    fun dismissModal() {
        _addTransactionUiState.update { currentState ->
            currentState.copy(
                showDateModal = false
            )
        }
    }

    fun dateInput(newDate: Long?) {
        _addTransactionUiState.update { currentState ->
            currentState.copy(
                selectedDate = newDate
            )
        }
    }

    fun onSubmit() {
        var success = true
        val current = _addTransactionUiState.value

        // 1. Calculate all errors first
        val nameErr = if (current.name.isBlank()) "Name must not be left blank" else null
        val amountErr = if (current.amount <= 0.0) "Amount must be greater than 0" else null
        val categoryErr = if (current.category == null) "Category must not be empty" else null
        val dateErr = if (current.selectedDate == null) "Date must not be empty" else null

        // 2. Check if any errors exist
        val hasError =
            nameErr != null || amountErr != null || categoryErr != null || dateErr != null

        if (hasError) {
            _addTransactionUiState.update {
                it.copy(
                    nameError = nameErr,
                    amountError = amountErr,
                    categoryError = categoryErr,
                    dateError = dateErr
                )
            }
            return // Stop right here
        }

        val newTransaction = Transaction(
            id = System.currentTimeMillis(),
            name = addTransactionUiState.value.name,
            amount = addTransactionUiState.value.amount,
            date = addTransactionUiState.value.selectedDate,
            category = addTransactionUiState.value.category
        )
        _transactionUiState.update { listState ->
            listState.copy(
                transactionList = listState.transactionList + newTransaction
            )
        }
        _addTransactionUiState.update {
            AddTransactionUiState(onSuccessSubmission = true)
        }
    }

    fun consumeSuccess() {
        _addTransactionUiState.value = AddTransactionUiState()
    }
}