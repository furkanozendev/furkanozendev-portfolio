package com.furkanozendev.feature.portfolio.presentation.home.components.projects

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkanozendev.core.designsystem.colors.HomeColors
import com.furkanozendev.feature.portfolio.presentation.home.widget.ProjectUiModel
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun ProjectCardGrid(
    project: ProjectUiModel,
    modifier: Modifier = Modifier,
    colors: HomeColors
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(colors.surfaceGlass)
            .border(1.dp, colors.borderPrimary, RoundedCornerShape(16.dp))
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
                    color = colors.textPrimary,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.weight(1f)
            )
        }

        ProjectStatusPill(project.status, project.statusStyle, colors)

        Text(
            text = project.description,
            style = MaterialTheme.typography.bodySmall.copy(
                color = colors.textSecondary,
                lineHeight = 18.sp
            ),
            maxLines = 4
        )

        ProjectTechRow(project.techStack, colors)

        ProjectActionsRow(project, colors)
    }
}

@Composable
internal fun ProjectBanner(
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
