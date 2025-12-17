package com.furkanozendev.feature.portfolio.presentation.home.widget.projects

import org.jetbrains.compose.resources.DrawableResource

data class ProjectsUiState(
    val title: String = "",
    val headerTitle: String = "",
    val headerSubtitle: String = "",
    val projects: List<ProjectItemUiState> = emptyList()
)

data class ProjectItemUiState(
    val bannerPainter: DrawableResource,
    val title: String,
    val status: String,
    val statusStyle: ProjectStatusStyle,
    val description: String,
    val techStack: List<String>,
    val primaryActionLabel: String? = null,
    val primaryActionUrl: String? = null,
    val secondaryActionLabel: String? = null,
    val secondaryActionUrl: String? = null
)
