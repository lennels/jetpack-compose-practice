package com.example.makansplitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.makansplitter.data.MakanItem
import com.example.makansplitter.data.MakanItemSource
import com.example.makansplitter.ui.theme.MakanSplitterTheme
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MakanSplitterApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakanSplitterApp() {
    MakanSplitterTheme {
        var orderList by remember { (mutableStateOf(MakanItemSource.menuItems)) }
        fun incrementQuantity(id: Int) {
            orderList = orderList.map { item ->
                if (item.id == id) item.copy(quantity = item.quantity + 1)
                else item
            }
        }

        fun decrementQuantity(id: Int) {
            orderList = orderList.map { item ->
                if (item.id == id && item.quantity > 0) item.copy(quantity = item.quantity - 1)
                else item
            }
        }
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Makan Splitter",
                            color = MaterialTheme.colorScheme.background
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                ItemList(
                    makanList = orderList,
                    onIncrement = { id -> incrementQuantity(id) },
                    onDecrement = { id -> decrementQuantity(id) },
                    modifier = Modifier.weight(1f)
                )
                BottomSummary(orderList)
            }
        }
    }
}

@Composable
fun ItemList(
    makanList: List<MakanItem>,
    onIncrement: (Int) -> Unit,
    onDecrement: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(12.dp)
    ) {
        items(items = makanList) { makan ->
            val (id, name, price, quantity) = makan
            ItemCard(
                id,
                name,
                price,
                quantity,
                onIncrement,
                onDecrement,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun ItemCard(
    id: Int,
    name: String,
    price: Double,
    quantity: Int,
    onIncrement: (Int) -> Unit,
    onDecrement: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column() {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    name,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text("$$price")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = { onIncrement(id) },
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = null
                    )
                }
                Text(quantity.toString())
                IconButton(
                    onClick = { onDecrement(id) },
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun BottomSummary(orderItem: List<MakanItem>, modifier: Modifier = Modifier) {
    fun Double.roundUp() = round(this * 100) / 100
    val subtotal = orderItem.filter { item ->
        item.quantity > 0
    }.sumOf { item ->
        item.quantity * item.price
    }.roundUp()
    val gst = (subtotal * 0.09).roundUp()
    val totalPrice = (subtotal + gst).roundUp()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp, bottomStart = 0.dp, bottomEnd = 0.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            SummaryRow("Subtotal", subtotal)
            SummaryRow("GST (9%)", gst)
            SummaryRow("Total Price", totalPrice)
        }
    }
}

@Composable
fun SummaryRow(title: String, sum: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text("$title:")
        Text("$$sum") // Sum of all price * quantity
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MakanSplitterTheme {
        MakanSplitterApp()
    }
}