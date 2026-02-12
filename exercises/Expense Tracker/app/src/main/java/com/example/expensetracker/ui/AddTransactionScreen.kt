package com.example.expensetracker.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensetracker.model.Category
import com.example.expensetracker.util.convertMillisToDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    viewModel: AddTransactionViewModel,
    navigateBack: () -> Unit,
) {
    val uiState by viewModel.addTransactionUiState.collectAsState()
    LaunchedEffect(uiState.onSuccessSubmission) {
        if (uiState.onSuccessSubmission) {
            navigateBack()
            viewModel.consumeSuccess()
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = uiState.name,
            onValueChange = { viewModel.nameTextbox(it) },
            label = { Text(text = "Name") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            isError = uiState.nameError != null,
            supportingText = {
                uiState.nameError?.let { error ->
                    Text(text = error)
                }
            }
        )
        OutlinedTextField(
            value = uiState.amountInput, // textbox = string
            onValueChange = { viewModel.amountTextbox(it) },
            label = { Text(text = "Amount") },
            prefix = { Text("$") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            isError = uiState.amountError != null,
            supportingText = {
                uiState.amountError?.let { error ->
                    Text(text = error)
                }
            }
        )
        ExposedDropdownMenuBox(
            expanded = uiState.categoryDropdownMenuSelected,
            onExpandedChange = { viewModel.activateCategoryDropBox() },
        ) {
            OutlinedTextField(
                value = if (uiState.category == null) "" else uiState.category.toString(),
                onValueChange = {},
                readOnly = true,
                label = { Text("Category") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = uiState.categoryDropdownMenuSelected) },
                modifier = Modifier.menuAnchor(), // Critical for positioning
                isError = uiState.categoryError != null,
                supportingText = {
                    uiState.categoryError?.let { error ->
                        Text(text = error)
                    }
                }
            )
            ExposedDropdownMenu(
                expanded = uiState.categoryDropdownMenuSelected,
                onDismissRequest = { viewModel.dismissCategoryDropBox() },
            ) {
                Category.entries.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(text = category.toString()) },
                        onClick = {
                            viewModel.selectCategory(category)
                        }
                    )
                }
            }
        }
        DatePickerFieldToModal(viewModel)
        Button(onClick = { viewModel.onSubmit() }) {
            Text("Submit")
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewTransaction() {
    val viewModel: AddTransactionViewModel = viewModel()
    MaterialTheme {
//        AddTransactionScreen(
//            viewModel = viewModel
//        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DateInputSample() {
//    DatePickerFieldToModal()
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerFieldToModal(viewModel: AddTransactionViewModel, modifier: Modifier = Modifier) {
    val uiState by viewModel.addTransactionUiState.collectAsState()
    OutlinedTextField(
        value = uiState.selectedDate?.let { convertMillisToDate(it) } ?: "",
        onValueChange = { },
        readOnly = true,
        label = { Text("Date") },
        isError = uiState.dateError != null,
        supportingText = {
            uiState.dateError?.let { error ->
                Text(text = error)
            }
        },
        placeholder = { Text("MM/DD/YYYY") },
        trailingIcon = {
            Icon(Icons.Default.DateRange, contentDescription = "Select date")
        },
        modifier = modifier
            .pointerInput(uiState.selectedDate) {
                awaitEachGesture {
                    // Modifier.clickable doesn't work for text fields, so we use Modifier.pointerInput
                    // in the Initial pass to observe events before the text field consumes them
                    // in the Main pass.
                    awaitFirstDown(pass = PointerEventPass.Initial)
                    val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                    if (upEvent != null) {
                        viewModel.showModal()
                    }
                }
            }
    )

    if (uiState.showDateModal) {
        DatePickerModal(
            onDateSelected = { viewModel.dateInput(it) },
            onDismiss = { viewModel.dismissModal() }
        )
    }
}

@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

