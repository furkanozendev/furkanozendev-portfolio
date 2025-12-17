package com.furkanozendev.feature.portfolio.presentation.home.widget.projects

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import org.jetbrains.compose.resources.DrawableResource
import org.koin.compose.viewmodel.koinViewModel

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
fun ProjectsWidget(
    modifier: Modifier = Modifier,
    viewModel: ProjectsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val uriHandler = LocalUriHandler.current
    val colors = LocalHomeColors.current

    val projects = remember(uiState) {
        uiState.projects.map { item ->
            ProjectUiModel(
                bannerPainter = item.bannerPainter,
                title = item.title,
                status = item.status,
                statusStyle = item.statusStyle,
                description = item.description,
                techStack = item.techStack,
                primaryActionLabel = item.primaryActionLabel,
                onPrimaryClick = item.primaryActionUrl?.let { url -> { uriHandler.openUri(url) } },
                secondaryActionLabel = item.secondaryActionLabel,
                onSecondaryClick = item.secondaryActionUrl?.let { url -> { uriHandler.openUri(url) } }
            )
        }
    }

    BentoCard(
        modifier = modifier,
        title = uiState.title,
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
                    title = uiState.headerTitle,
                    subtitle = uiState.headerSubtitle,
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