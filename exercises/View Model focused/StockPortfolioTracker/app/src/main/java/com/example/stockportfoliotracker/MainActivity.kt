package com.example.stockportfoliotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.example.stockportfoliotracker.ui.StockViewModel
import com.example.stockportfoliotracker.ui.theme.StockPortfolioTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StockPortfolioTrackerTheme {
                StockScreen()
            }
        }
    }
}
@Composable
fun StockScreen(viewModel: StockViewModel = viewModel()) {
    val state by viewModel.stockUiState.collectAsState()

    // Theme color based on market performance
    val marketColor = if (state.isBullMarket) Color(0xFF4CAF50) else Color(0xFFF44336)

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Portfolio Tracker", style = MaterialTheme.typography.headlineMedium)

        // Portfolio Overview Card
        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            colors = CardDefaults.cardColors(containerColor = marketColor.copy(alpha = 0.1f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Total Profit/Loss", style = MaterialTheme.typography.labelMedium)
                Text(
                    text = "$${String.format("%.2f", state.totalProfitLoss)}",
                    style = MaterialTheme.typography.displaySmall,
                    color = marketColor,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (state.isBullMarket) "Bull Market 📈" else "Bear Market 📉",
                    color = marketColor
                )
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
    StockPortfolioTrackerTheme {
        Greeting("Android")
    }
}