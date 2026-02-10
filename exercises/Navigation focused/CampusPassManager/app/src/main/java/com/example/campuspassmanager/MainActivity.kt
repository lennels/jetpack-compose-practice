package com.example.campuspassmanager

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.campuspassmanager.model.CampusPass
import com.example.campuspassmanager.ui.PassViewModel
import com.example.campuspassmanager.ui.theme.CampusPassManagerTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampusPassManagerTheme {
                // Initialize the ViewModel here so it's shared
                val viewModel: PassViewModel = viewModel()
                Surface(
                    modifier = Modifier.fillMaxSize().statusBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Call YOUR NavHost, not the system Greeting
                    NavHost(sharedViewModel = viewModel)
                }
            }
        }
    }
}

@Serializable
object PassList

@Serializable
data class PassDetailsScreen(val id: String)

@Composable
fun NavHost(
    navController: NavHostController = rememberNavController(),
    sharedViewModel: PassViewModel
) {
    val uiState by sharedViewModel.passUiState.collectAsState()
    NavHost(
        navController = navController,
        startDestination = PassList
    ) {
        composable<PassList> {
            PassWallet(
                viewModel = sharedViewModel,
                onWalletClick = { id ->
                    navController.navigate(PassDetailsScreen(id = id))
                }
            )
        }
        composable<PassDetailsScreen> { backStackEntry ->
            val detailRoute: PassDetailsScreen = backStackEntry.toRoute()
            PassDetailsScreen(
                id = detailRoute.id,
                viewModel = sharedViewModel,
                onBackButton = {
                    navController.popBackStack()
                },
            )
        }
    }
}

@Composable
fun PassWallet(
    viewModel: PassViewModel = viewModel(),
    onWalletClick: (String) -> Unit
) {
    val uiState by viewModel.passUiState.collectAsState()
    LazyColumn() {
        items(items = uiState.passList) { pass ->
            PassCard(
                campusPass = pass,
                onWalletCard = { onWalletClick(pass.id) }
            )
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun PassCard(
    campusPass: CampusPass,
    onWalletCard: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable { onWalletCard() }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "${campusPass.title} card",
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "$${String.format("%.2f", campusPass.balance)}",
            )
        }
    }
}

@Composable
fun PassDetailsScreen(
    id: String,
    viewModel: PassViewModel = viewModel(),
    onBackButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.passUiState.collectAsState()

    val pass = uiState.passList.find { it.id == id }
    if (pass != null) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(pass.color)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = "${pass.title} card",
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "$${String.format("%.2f", pass.balance)}",
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Button(onClick = onBackButton) {
                        Text("Return")
                    }
                    Button(onClick = { viewModel.topUpPass(passId = pass.id) }) {
                        Text("Top up $10")
                    }
                }
            }
        }
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
    CampusPassManagerTheme {
        PassWallet(onWalletClick = {})
    }
}