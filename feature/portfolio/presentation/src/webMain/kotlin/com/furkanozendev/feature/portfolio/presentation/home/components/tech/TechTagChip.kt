package com.furkanozendev.feature.portfolio.presentation.home.components.tech

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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

@Composable
internal fun TechTagChip(text: String, colors: HomeColors) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(colors.surfaceChip)
            .border(1.dp, colors.borderSecondary, RoundedCornerShape(999.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                color = colors.textPrimary,
                fontWeight = FontWeight.Medium
            )
        )
    }
}
