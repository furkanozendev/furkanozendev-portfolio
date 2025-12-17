package com.furkanozendev.feature.portfolio.presentation.home.widget.experience

data class ExperienceUiState(
    val title: String = "",
    val headerTitle: String = "",
    val headerSubtitle: String = "",
    val company: String = "",
    val companyPeriod: String = "",
    val roles: List<ExperienceRoleUiState> = emptyList()
)

data class ExperienceSectionUiState(
    val heading: String,
    val highlight: String?,
    val bullets: List<String> = emptyList()
)

data class ExperienceRoleUiState(
    val title: String,
    val date: String,
    val subtitle: String? = null,
    val sections: List<ExperienceSectionUiState>,
    val keyWins: List<String>,
    val keyWinsTitle: String,
    val focusAreasLabel: String,
    val isIntern: Boolean = false
)