package com.furkanozendev.feature.portfolio.presentation.home.components.experience

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.furkanozendev.core.designsystem.colors.HomeColors

@Composable
internal fun RoleHeader(
    title: String,
    date: String,
    subtitle: String?,
    colors: HomeColors
) {
    Text(
        title,
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Medium,
            color = colors.textPrimary
        )
    )
    Text(
        date,
        style = MaterialTheme.typography.bodySmall.copy(color = colors.textMuted)
    )

    if (!subtitle.isNullOrBlank()) {
        Text(
            subtitle,
            style = MaterialTheme.typography.bodySmall.copy(
                color = colors.textSecondary,
                lineHeight = 18.sp
            )
        )
    }
}
