package com.furkanozendev.feature.portfolio.presentation.home.widget.projects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkanozendev.feature.portfolio.domain.usecase.ObserveStringResourcesUseCase
import com.furkanozendev.feature.portfolio.presentation.home.infra.MapStringResourceManager
import furkanozendev.feature.portfolio.presentation.generated.resources.Res
import furkanozendev.feature.portfolio.presentation.generated.resources.project_duelist_banner
import furkanozendev.feature.portfolio.presentation.generated.resources.project_github_banner
import furkanozendev.feature.portfolio.presentation.generated.resources.project_nexkmp_banner
import furkanozendev.feature.portfolio.presentation.generated.resources.project_portfolio_banner
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ProjectsViewModel(
    observeStrings: ObserveStringResourcesUseCase
) : ViewModel() {

    val uiState: StateFlow<ProjectsUiState> = observeStrings()
        .map { stringMap ->
            val res = MapStringResourceManager(stringMap)
            ProjectsUiState(
                title = res["projects_title"],
                headerTitle = res["projects_header_title"],
                headerSubtitle = res["projects_header_subtitle"],
                projects = listOf(
                    ProjectItemUiState(
                        bannerPainter = Res.drawable.project_portfolio_banner,
                        title = res["projects_portfolio_title"],
                        status = res["projects_portfolio_status"],
                        statusStyle = ProjectStatusStyle.Live,
                        description = res["projects_portfolio_desc"],
                        techStack = res.getList("projects_portfolio_tech"),
                        primaryActionLabel = res["projects_view_live"],
                        primaryActionUrl = res["projects_portfolio_url"],
                        secondaryActionLabel = res["projects_view_code"],
                        secondaryActionUrl = res["projects_portfolio_github_url"]
                    ),
                    ProjectItemUiState(
                        bannerPainter = Res.drawable.project_duelist_banner,
                        title = res["projects_duelist_title"],
                        status = res["projects_duelist_status"],
                        statusStyle = ProjectStatusStyle.Private,
                        description = res["projects_duelist_desc"],
                        techStack = res.getList("projects_duelist_tech")
                    ),
                    ProjectItemUiState(
                        bannerPainter = Res.drawable.project_nexkmp_banner,
                        title = res["projects_nexkmp_title"],
                        status = res["projects_nexkmp_status"],
                        statusStyle = ProjectStatusStyle.Neutral,
                        description = res["projects_nexkmp_desc"],
                        techStack = res.getList("projects_nexkmp_tech"),
                        primaryActionLabel = res["projects_view_code"],
                        primaryActionUrl = res["projects_nexkmp_url"]
                    ),
                    ProjectItemUiState(
                        bannerPainter = Res.drawable.project_github_banner,
                        title = res["projects_github_title"],
                        status = res["projects_github_status"],
                        statusStyle = ProjectStatusStyle.Neutral,
                        description = res["projects_github_desc"],
                        techStack = res.getList("projects_github_tech"),
                        primaryActionLabel = res["projects_open_github"],
                        primaryActionUrl = res["projects_github_url"]
                    )
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ProjectsUiState()
        )
}
