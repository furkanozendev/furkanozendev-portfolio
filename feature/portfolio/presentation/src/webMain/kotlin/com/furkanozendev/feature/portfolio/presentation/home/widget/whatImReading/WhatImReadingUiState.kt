package com.furkanozendev.feature.portfolio.presentation.home.widget.whatImReading

data class WhatImReadingUiState(
    val title: String = "",
    val headerSubtitle: String = "",
    val emptyTitle: String = "",
    val emptySubtitle: String = "",
    val items: List<ReadingItemUiState> = emptyList()
)

data class ReadingItemUiState(
    val id: String,
    val title: String,
    val source: String,
    val url: String,
    val tags: List<String> = emptyList()
)