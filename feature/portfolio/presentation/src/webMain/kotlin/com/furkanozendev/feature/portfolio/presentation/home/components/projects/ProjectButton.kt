package com.furkanozendev.feature.portfolio.presentation.home.components.projects

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.furkanozendev.core.designsystem.colors.HomeColors
import com.furkanozendev.feature.portfolio.presentation.home.widget.projects.ProjectUiModel

@Composable
internal fun ProjectActionsRow(project: ProjectUiModel, colors: HomeColors) {
    val hasPrimary = project.primaryActionLabel != null && project.onPrimaryClick != null
    if (!hasPrimary) return

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        ProjectButton(
            label = project.primaryActionLabel,
            filled = true,
            onClick = project.onPrimaryClick,
            colors = colors
        )

        if (project.secondaryActionLabel != null && project.onSecondaryClick != null) {
            ProjectButton(
                label = project.secondaryActionLabel,
                filled = false,
                onClick = project.onSecondaryClick,
                colors = colors
            )
        }
    }
}

@Composable
internal fun ProjectButton(
    label: String,
    filled: Boolean,
    onClick: () -> Unit,
    colors: HomeColors
) {
    val bg = if (filled) colors.accentPrimary else Color.Transparent
    val fg = if (filled) colors.textOnAccent else colors.accentPrimary
    val border = if (filled) Color.Transparent else colors.accentPrimary

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
