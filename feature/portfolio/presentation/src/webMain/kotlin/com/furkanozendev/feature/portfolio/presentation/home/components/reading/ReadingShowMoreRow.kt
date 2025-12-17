package com.furkanozendev.feature.portfolio.presentation.home.components.reading

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.furkanozendev.feature.portfolio.presentation.home.infra.LocalStringResources

@Composable
internal fun ReadingShowMoreRow(
    expanded: Boolean,
    remainingCount: Int,
    onToggle: () -> Unit,
    colors: HomeColors
) {
    val res = LocalStringResources.current
    val label = when {
        expanded -> res["reading_show_less"]
        remainingCount > 0 -> res["reading_show_n_more", listOf(remainingCount.toString())]
        else -> res["reading_show_more"]
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(999.dp))
                .background(colors.surfaceGlassMuted)
                .border(1.dp, colors.borderSecondary, RoundedCornerShape(999.dp))
                .clickable(onClick = onToggle)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium.copy(
                    color = colors.textMuted,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}
