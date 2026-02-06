package com.example.lioncitywallet

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lioncitywallet.data.WalletDataSource
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lioncitywallet.data.PaymentCard
import com.example.lioncitywallet.data.Transaction
import com.example.ui.theme.AppTypography
import com.example.ui.theme.bodyFontFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LionCityWalletApp()
        }
    }


    @Composable
    fun LionCityWalletApp() {
        var selectedPaymentCard by remember { mutableStateOf("Unselected") }
        MaterialTheme {
            Scaffold(
                topBar = {
                    TopAppBar()
                },
                modifier = Modifier
                    .fillMaxSize()
            ) { innerPadding ->
                Column(modifier = Modifier
                    .padding(innerPadding)) {
                    VerticalCardRow(
                        selectedPaymentCard,
                        { newValue ->
                            if (selectedPaymentCard == newValue)
                                selectedPaymentCard = "Unselected"
                            else selectedPaymentCard = newValue
                        },
                    )
                    ColumnTransactions(selectedPaymentCard, WalletDataSource.transactions)
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppBar() {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    "Lion City Wallet",
                    style = MaterialTheme.typography.displayMedium
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
    }


    @Composable
    fun VerticalCardRow(
        selectedPaymentCard: String,
        selectPaymentCard: (String) -> Unit,
        modifier: Modifier = Modifier
    ) {
        LazyRow(modifier = modifier) {
            items(items = WalletDataSource.cards) {
                CardButton(
                    selectedPaymentCard = selectedPaymentCard,
                    selectPaymentCard,
                    it,
                )
            }
        }
    }

    @Composable
    fun CardButton(
        selectedPaymentCard: String,
        selectPaymentCard: (String) -> Unit,
        card: PaymentCard,
        modifier: Modifier = Modifier
    ) {
        Button(
            onClick = { selectPaymentCard(card.cardType) },
            colors = ButtonDefaults.buttonColors(
                card.color
            ),
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            Text(
                text = "${card.cardType} (${card.lastFour})",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(8.dp),
            )
        }
    }

    @Composable
    fun ColumnTransactions(
        selectedPaymentCard: String,
        transactionList: List<Transaction>,
        modifier: Modifier = Modifier
    ) {
        LazyColumn() {
            items(items = transactionList) { transaction ->
                val (merchant, amount, category, card) = transaction
                if (selectedPaymentCard == card.cardType)
                    TransactionCard(merchant, amount, category, card)
                else if (selectedPaymentCard == "Unselected")
                    TransactionCard(merchant, amount, category, card)

            }
        }
    }

    @Composable
    fun TransactionCard(merchant: String, amount: Double, category: String, card: PaymentCard) {
        Card(
            colors = CardDefaults.cardColors(
                card.color
            ),
            modifier = Modifier
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp
                ),
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = merchant,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = card.cardType,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = category,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = "$$amount",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        MaterialTheme() {
            LionCityWalletApp()
        }
    }
}

