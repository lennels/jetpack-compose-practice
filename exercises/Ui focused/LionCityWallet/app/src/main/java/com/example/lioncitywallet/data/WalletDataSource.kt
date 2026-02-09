package com.example.lioncitywallet.data

import androidx.compose.ui.graphics.Color

object WalletDataSource {

    // 1. Define our available cards (The "Wallet")
    val cards = listOf(
        PaymentCard("Visa", "4242", Color(0xFF1A1F71)),       // Classic Visa Blue
        PaymentCard("Mastercard", "8899", Color(0xFFEB001B)), // Mastercard Red
        PaymentCard("American Express", "1007", Color(0xFF007BC1)) // Amex Bright Blue
    )

    // 2. Define our transactions
    val transactions = listOf(
        Transaction("FairPrice Hub", 45.50, "Food", cards[0]),
        Transaction("Grab Ride", 18.20, "Transport", cards[1]),
        Transaction("Shake Shack Jewel", 32.00, "Food", cards[0]),
        Transaction("Uniqlo Orchard", 159.90, "Shopping", cards[2]),
        Transaction("Old Seng Choong", 25.00, "Food", cards[1]),
        Transaction("MRT Tap-in", 1.80, "Transport", cards[0]),
        Transaction("Telok Ayer Cafe", 7.50, "Food", cards[2]),
        Transaction("Suntec City Parking", 4.20, "Transport", cards[1]),
        Transaction("Lazada SG", 88.00, "Shopping", cards[0]),
        Transaction("Bacha Coffee", 12.50, "Food", cards[2])
    )
}