package com.example.mallsapp.ui

import com.example.mallsapp.model.Mall

data class MallUiState(
    val selectedMall: Mall,
//    val mallList: List<Mall> = ,
    val isShowingListPage: Boolean = true
)
