package com.furkanozendev.feature.portfolio.presentation.home.components.experience

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.furkanozendev.core.designsystem.colors.HomeColors

@Composable
internal fun BulletText(text: String, colors: HomeColors) {
    Text(
        text = "• $text",
        style = MaterialTheme.typography.bodySmall.copy(
            color = colors.textSecondary,
            lineHeight = 18.sp
        )
    )
}
