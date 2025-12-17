package com.furkanozendev.feature.portfolio.presentation.home.components.projects

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.furkanozendev.core.designsystem.colors.HomeColors

@Composable
internal fun ProjectTechRow(techStack: List<String>, colors: HomeColors) {
    val visible = techStack.take(5)
    val remaining = techStack.size - visible.size

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        visible.forEach { ProjectTechChip(it, colors = colors) }
        if (remaining > 0) {
            ProjectTechChip("+$remaining more", muted = true, colors = colors)
        }
    }
}

@Composable
internal fun ProjectTechChip(text: String, muted: Boolean = false, colors: HomeColors) {
    val bg = if (muted) colors.surfaceGlass else colors.surfaceChip
    val fg = if (muted) colors.textMuted else colors.textPrimary

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(bg)
            .border(1.dp, colors.borderSecondary, RoundedCornerShape(999.dp))
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
