package com.example.expensetracker.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Converts Milliseconds (Long) to a formatted String.
 * Useful for timestamps from APIs or System.currentTimeMillis().
 */
@RequiresApi(Build.VERSION_CODES.O)
fun convertMillisToDate(millis: Long): String {
    val instant = Instant.ofEpochMilli(millis)
    val date = instant.atZone(ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
    return date.format(formatter)
}

/**
 * Converts Epoch Days (Long) to a formatted String.
 * Perfect for the data you just stored in TransactionDataSource.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun Long.toFormattedDateString(): String {
    val date = LocalDate.ofEpochDay(this)
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    return date.format(formatter)
}