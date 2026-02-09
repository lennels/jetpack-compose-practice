package com.example.librarymemberportal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import com.example.librarymemberportal.ui.theme.LibraryMemberPortalTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.librarymemberportal.ui.LibraryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LibraryMemberPortalTheme {
                LibraryScreen()
            }
        }
    }
}

@Composable
fun LibraryScreen(viewModel: LibraryViewModel = viewModel()) {
    val state by viewModel.libraryUiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Membership Header
        Text(
            text = state.membership?.tierName ?: "Loading...",
            style = MaterialTheme.typography.headlineLarge
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = if (state.isGoodStanding)
                    MaterialTheme.colorScheme.surfaceVariant
                else
                    MaterialTheme.colorScheme.errorContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Books Borrowed: ${state.booksBorrowed} / ${state.membership?.maxBooksAllowed ?: 0}")
                Text("Late Days: ${state.daysLate}")

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                Text(
                    text = "Current Fine: $${state.currentFine}",
                    style = MaterialTheme.typography.titleLarge,
                    color = if (state.currentFine > 0) Color.Red else Color.Unspecified
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Actions
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // Borrow Button - Disables itself using your 'canBorrowMore' logic!
            Button(
                onClick = { viewModel.borrowBook() },
                enabled = state.canBorrowMore
            ) {
                Text("Borrow Book")
            }

            OutlinedButton(onClick = { viewModel.returnBook() }) {
                Text("Return Book")
            }
        }

        Button(
            onClick = { viewModel.incrementLateDays() },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
        ) {
            Text("Simulate 1 Late Day")
        }

        if (!state.canBorrowMore) {
            Text(
                text = "Maximum borrowing limit reached!",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LibraryMemberPortalTheme {
//        Greeting("Android")
    }
}