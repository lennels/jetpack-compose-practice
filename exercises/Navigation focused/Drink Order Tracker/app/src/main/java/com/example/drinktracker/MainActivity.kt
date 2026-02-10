package com.example.drinktracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.drinktracker.ui.theme.DrinkTrackerTheme
// Navigation & State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
// Layout & UI
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.navigationevent.compose.rememberNavigationEventState
// Your own models
import com.example.drinktracker.model.Drink
import com.example.drinktracker.ui.DrinkViewModel
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrinkTrackerTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
                DrinkNavHost()
            }
        }
    }
}

@Serializable
object DrinkList

@Serializable
data class DrinkDetail(val drinkId: String)

@Composable
fun DrinkNavHost() {
    val navController = rememberNavController()
    val sharedViewModel: DrinkViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = DrinkList
    ) {
        composable<DrinkList> {
            DrinkListScreen(
                viewModel = sharedViewModel,
                onDrinkClick = { id ->
                    navController.navigate(DrinkDetail(drinkId = id))
                }
            )
        }
        composable<DrinkDetail> { backStackEntry ->
            val detailRoute: DrinkDetail = backStackEntry.toRoute()
            DrinkDetailScreen(
                drinkId = detailRoute.drinkId,
                viewModel = sharedViewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
fun DrinkListScreen(
    viewModel: DrinkViewModel = viewModel(),
    onDrinkClick: (String) -> Unit // This is a "hoisted" event for navigation later!
) {
    val uiState by viewModel.drinkUiModel.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState.isLoading) {
            // Show the SIT loading spinner
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Text(
                        text = "SIT Latte Menu",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                items(uiState.orderDetails) { drink ->
                    DrinkItem(
                        drink = drink,
                        onClick = { onDrinkClick(drink.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun DrinkItem(drink: Drink, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = drink.name, style = MaterialTheme.typography.titleLarge)
                Text(text = "Size: ${drink.size}", style = MaterialTheme.typography.bodyMedium)
            }
            Text(
                text = "$${String.format("%.2f", drink.price)}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun DrinkDetailScreen(
    drinkId: String,
    viewModel: DrinkViewModel = viewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.drinkUiModel.collectAsState()

    // Find the specific drink from the list using the ID passed via Navigation
    val drink = uiState.orderDetails.find { it.id == drinkId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (drink != null) {
            Text(text = drink.name, style = MaterialTheme.typography.displayMedium)
            Text(text = "Current Size: ${drink.size}")
            Text(text = "Price: $${drink.price}")

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = { viewModel.upgradeOrder(drink.id) }) {
                Text("Upgrade to Large (+$1.50)")
            }
        }

        TextButton(onClick = onBackClick) {
            Text("Go Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    DrinkListScreen(onDrinkClick = {})
}