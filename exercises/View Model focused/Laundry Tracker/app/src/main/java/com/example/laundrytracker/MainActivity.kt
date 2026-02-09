package com.example.laundrytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.laundrytracker.ui.theme.BookTrackerTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.laundrytracker.ui.LaundryViewModel
import androidx.compose.foundation.layout.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookTrackerTheme {
                LaundryScreen()
            }
        }
    }
}



@Composable
fun LaundryScreen(viewModel: LaundryViewModel = viewModel()) {
    // 1. We observe the state from the ViewModel
    val state by viewModel.laundryUiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display the Cycle Name
        Text(
            text = state.cycleName,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Display the Total Time (Automatically updated by your get() property!)
        Text(
            text = "${state.totalTime} Minutes",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Base: ${state.baseMinutes}m | Extra: +${state.extraRinseCount * state.extraRinseMinutes}m",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Button to Remove Rinse
            Button(onClick = { viewModel.removeRinse() }) {
                Text("- Remove Rinse")
            }

            // Current Rinse Count
            Text(
                text = "${state.extraRinseCount}",
                style = MaterialTheme.typography.titleLarge
            )

            // Button to Add Rinse
            Button(onClick = { viewModel.addRinse() }) {
                Text("+ Add Rinse")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Each extra rinse adds ${state.extraRinseMinutes} minutes",
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookTrackerTheme {
        LaundryScreen()
    }
}