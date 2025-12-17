package com.furkanozendev.feature.portfolio.presentation.home.components.projects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import com.furkanozendev.feature.portfolio.presentation.home.widget.ProjectStatusStyle

@Composable
internal fun ProjectStatusPill(text: String, style: ProjectStatusStyle, colors: HomeColors) {
    val (bg, fg) = when (style) {
        ProjectStatusStyle.Live -> colors.statusLiveBg to colors.statusLiveText
        ProjectStatusStyle.InProgress -> colors.statusProgressBg to colors.statusProgressText
        ProjectStatusStyle.Private -> colors.statusPrivateBg to colors.statusPrivateText
        ProjectStatusStyle.Neutral -> colors.statusNeutralBg to colors.statusNeutralText
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
