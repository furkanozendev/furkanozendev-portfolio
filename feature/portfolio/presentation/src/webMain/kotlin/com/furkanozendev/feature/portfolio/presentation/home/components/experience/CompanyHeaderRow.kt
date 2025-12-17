package com.furkanozendev.feature.portfolio.presentation.home.components.experience

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.furkanozendev.core.designsystem.colors.HomeColors

@Composable
internal fun CompanyHeaderRow(
    logo: Painter,
    company: String,
    period: String,
    colors: HomeColors
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = logo,
            contentDescription = company,
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Spacer(Modifier.width(14.dp))
        Column {
            Text(
                company,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = colors.textPrimary
                )
            )
            Text(
                period,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = colors.textMuted
                )
            )
        }
    }
}
