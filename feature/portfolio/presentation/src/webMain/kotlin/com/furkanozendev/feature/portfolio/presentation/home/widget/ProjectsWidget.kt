package com.furkanozendev.feature.portfolio.presentation.home.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Apps
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkanozendev.core.designsystem.components.SectionHeader
import com.furkanozendev.feature.portfolio.presentation.home.components.BentoCard
import com.furkanozendev.feature.portfolio.presentation.home.infra.LocalStringResources
import furkanozendev.feature.portfolio.presentation.generated.resources.Res
import furkanozendev.feature.portfolio.presentation.generated.resources.project_duelist_banner
import furkanozendev.feature.portfolio.presentation.generated.resources.project_github_banner
import furkanozendev.feature.portfolio.presentation.generated.resources.project_portfolio_banner
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

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

    val textPrimary = Color(0xFFEDEDED)
    val textMuted = Color(0xFFB0B0B0)

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
                    textPrimary = textPrimary,
                    textMuted = textMuted
                )

                ProjectsGrid(
                    projects = projects,
                    columns = columns,
                    gap = gap
                )
            }
        }
    }
}

@Composable
private fun ProjectsGrid(
    projects: List<ProjectUiModel>,
    columns: Int,
    gap: Dp
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
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    )
                }
                repeat(columns - rowItems.size) {
                    Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun ProjectCardGrid(
    project: ProjectUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White.copy(alpha = 0.04f))
            .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ProjectBanner(
            bannerPainter = painterResource(project.bannerPainter),
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = project.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.weight(1f)
            )
        }

        ProjectStatusPill(project.status, project.statusStyle)

        Text(
            text = project.description,
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color(0xFFD8D8D8),
                lineHeight = 18.sp
            ),
            maxLines = 4
        )

        ProjectTechRow(project.techStack)

        ProjectActionsRow(project)
    }
}

@Composable
private fun ProjectBanner(
    bannerPainter: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = bannerPainter,
        contentDescription = null,
        modifier = modifier.clip(RoundedCornerShape(12.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun ProjectTechRow(techStack: List<String>) {
    val visible = techStack.take(5)
    val remaining = techStack.size - visible.size

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        visible.forEach { ProjectTechChip(it) }
        if (remaining > 0) {
            ProjectTechChip("+$remaining more", muted = true)
        }
    }
}

@Composable
private fun ProjectTechChip(text: String, muted: Boolean = false) {
    val bg = if (muted) Color.White.copy(alpha = 0.04f) else Color.White.copy(alpha = 0.06f)
    val border = Color.White.copy(alpha = 0.10f)
    val fg = if (muted) Color(0xFFB0B0B0) else Color(0xFFEDEDED)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(bg)
            .border(1.dp, border, RoundedCornerShape(999.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                color = fg,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
private fun ProjectStatusPill(text: String, style: ProjectStatusStyle) {
    val (bg, fg) = when (style) {
        ProjectStatusStyle.Live -> Color(0x3327AE60) to Color(0xFF2ECC71)
        ProjectStatusStyle.InProgress -> Color(0x33F1C40F) to Color(0xFFF1C40F)
        ProjectStatusStyle.Private -> Color(0x334E5DFF) to Color(0xFF8BE9FD) // cyan/blue
        ProjectStatusStyle.Neutral -> Color(0x33444444) to Color(0xFFDDDDDD)
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(bg)
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                color = fg,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Composable
private fun ProjectActionsRow(project: ProjectUiModel) {
    val hasPrimary = project.primaryActionLabel != null && project.onPrimaryClick != null
    if (!hasPrimary) return

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        ProjectButton(
            label = project.primaryActionLabel,
            filled = true,
            onClick = project.onPrimaryClick
        )

        if (project.secondaryActionLabel != null && project.onSecondaryClick != null) {
            ProjectButton(
                label = project.secondaryActionLabel,
                filled = false,
                onClick = project.onSecondaryClick
            )
        }
    }
}

@Composable
private fun ProjectButton(
    label: String,
    filled: Boolean,
    onClick: () -> Unit
) {
    val bg = if (filled) Color(0xFF8BE9FD) else Color.Transparent
    val fg = if (filled) Color(0xFF101015) else Color(0xFF8BE9FD)
    val border = if (filled) Color.Transparent else Color(0xFF8BE9FD)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(bg)
            .border(
                width = 1.dp,
                color = border,
                shape = RoundedCornerShape(999.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium.copy(
                color = fg,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}