package com.example.mallsapp.ui

import com.example.mallsapp.model.Mall

data class MallUiState(
    val selectedMall: Mall? = null,
    val mallList: List<Mall> = emptyList(),
    val isShowingListPage: Boolean = true
)
