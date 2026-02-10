package com.example.campuspassmanager.data

import com.example.campuspassmanager.model.CampusPass

object PassDataSource {
    val samplePasses = listOf(
        CampusPass("1", "SIT Student ID", "Lennel", 12.50, 0xFFBB86FC),
        CampusPass("2", "Gym Membership", "Lennel", 0.00, 0xFF03DAC5),
        CampusPass("3", "Library Access", "Lennel", 5.20, 0xFFCF6679)
    )
}