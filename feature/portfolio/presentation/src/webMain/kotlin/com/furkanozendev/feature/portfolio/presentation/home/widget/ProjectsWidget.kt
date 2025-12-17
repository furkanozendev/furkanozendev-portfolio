package com.furkanozendev.feature.portfolio.presentation.home.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Apps
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.furkanozendev.core.designsystem.colors.HomeColors
import com.furkanozendev.core.designsystem.colors.LocalHomeColors
import com.furkanozendev.core.designsystem.components.SectionHeader
import com.furkanozendev.feature.portfolio.presentation.home.components.BentoCard
import com.furkanozendev.feature.portfolio.presentation.home.components.projects.ProjectCardGrid
import com.furkanozendev.feature.portfolio.presentation.home.infra.LocalStringResources
import furkanozendev.feature.portfolio.presentation.generated.resources.Res
import furkanozendev.feature.portfolio.presentation.generated.resources.project_duelist_banner
import furkanozendev.feature.portfolio.presentation.generated.resources.project_github_banner
import furkanozendev.feature.portfolio.presentation.generated.resources.project_portfolio_banner
import org.jetbrains.compose.resources.DrawableResource

enum class ProjectStatusStyle { Live, InProgress, Neutral, Private }

data class ProjectUiModel(
    val bannerPainter: DrawableResource,
    val title: String,
    val status: String,
    val statusStyle: ProjectStatusStyle,
    val description: String,
    val techStack: List<String>,
    val primaryActionLabel: String? = null,
    val onPrimaryClick: (() -> Unit)? = null,
    val secondaryActionLabel: String? = null,
    val onSecondaryClick: (() -> Unit)? = null
)

@Composable
fun ProjectsWidget(modifier: Modifier = Modifier) {
    val res = LocalStringResources.current
    val uriHandler = LocalUriHandler.current

    val projects = remember(res) {
        listOf(
            ProjectUiModel(
                bannerPainter = Res.drawable.project_portfolio_banner,
                title = res["projects_portfolio_title"],
                status = res["projects_portfolio_status"],
                statusStyle = ProjectStatusStyle.Live,
                description = res["projects_portfolio_desc"],
                techStack = res.getList("projects_portfolio_tech"),
                primaryActionLabel = res["projects_view_live"],
                onPrimaryClick = { uriHandler.openUri(res["projects_portfolio_url"]) },
                secondaryActionLabel = res["projects_view_code"],
                onSecondaryClick = { uriHandler.openUri(res["projects_portfolio_github_url"]) }
            ),
            ProjectUiModel(
                bannerPainter = Res.drawable.project_duelist_banner,
                title = res["projects_duelist_title"],
                status = res["projects_duelist_status"],
                statusStyle = ProjectStatusStyle.Private,
                description = res["projects_duelist_desc"],
                techStack = res.getList("projects_duelist_tech")
            ),
            ProjectUiModel(
                bannerPainter = Res.drawable.project_github_banner,
                title = res["projects_nexkmp_title"],
                status = res["projects_nexkmp_status"],
                statusStyle = ProjectStatusStyle.Neutral,
                description = res["projects_nexkmp_desc"],
                techStack = res.getList("projects_nexkmp_tech"),
                primaryActionLabel = res["projects_view_code"],
                onPrimaryClick = { uriHandler.openUri(res["projects_nexkmp_url"]) }
            ),
            ProjectUiModel(
                bannerPainter = Res.drawable.project_github_banner,
                title = res["projects_github_title"],
                status = res["projects_github_status"],
                statusStyle = ProjectStatusStyle.Neutral,
                description = res["projects_github_desc"],
                techStack = res.getList("projects_github_tech"),
                primaryActionLabel = res["projects_open_github"],
                onPrimaryClick = { uriHandler.openUri(res["projects_github_url"]) }
            )
        )
    }

    val colors = LocalHomeColors.current

    BentoCard(
        modifier = modifier,
        title = res["projects_title"],
        icon = Icons.Rounded.Apps
    ) {
        BoxWithConstraints(Modifier.fillMaxWidth()) {
            val compact = maxWidth < 600.dp
            val gap = if (compact) 12.dp else 16.dp
            val padding = if (compact) 18.dp else 22.dp
            val columns = if (compact) 1 else 2

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SectionHeader(
                    title = res["projects_header_title"],
                    subtitle = res["projects_header_subtitle"],
                    textPrimary = colors.textPrimary,
                    textMuted = colors.textMuted
                )

                ProjectsGrid(
                    projects = projects,
                    columns = columns,
                    gap = gap,
                    colors = colors
                )
            }
        }
    }
}

@Composable
private fun ProjectsGrid(
    projects: List<ProjectUiModel>,
    columns: Int,
    gap: Dp,
    colors: HomeColors
) {
    val rows = remember(projects, columns) { projects.chunked(columns) }

    Column(verticalArrangement = Arrangement.spacedBy(gap)) {
        rows.forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(gap)
            ) {
                rowItems.forEach { project ->
                    ProjectCardGrid(
                        project = project,
                        modifier = Modifier.weight(1f).fillMaxHeight(),
                        colors = colors
                    )
                }
                repeat(columns - rowItems.size) {
                    Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}