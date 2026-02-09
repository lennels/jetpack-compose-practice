package com.example.librarymemberportal.data

import com.example.librarymemberportal.model.Membership

object LibraryDataSource {
    val membershipData = Membership(
        tierName = "Gold Member",
        maxBooksAllowed = 5,
        finePerLateDay = 2
    )
}