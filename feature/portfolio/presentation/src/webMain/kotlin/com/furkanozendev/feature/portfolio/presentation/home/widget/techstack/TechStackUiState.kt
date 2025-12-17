package com.furkanozendev.feature.portfolio.presentation.home.widget.techstack

import com.furkanozendev.feature.portfolio.presentation.home.components.tech.TechCategoryUi

data class TechStackUiState(
    val title: String = "",
    val headerTitle: String = "",
    val headerSubtitle: String = "",
    val primaryLabel: String = "",
    val secondaryLabel: String = "",
    val categories: List<TechCategoryUi> = emptyList()
)
