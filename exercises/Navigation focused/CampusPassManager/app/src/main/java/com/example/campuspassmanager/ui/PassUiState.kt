package com.example.campuspassmanager.ui

import com.example.campuspassmanager.model.CampusPass

data class PassUiState(
    val passList: List<CampusPass> = emptyList()
) {
    val isBroke = passList.any { it.balance < 10.0 }
}
