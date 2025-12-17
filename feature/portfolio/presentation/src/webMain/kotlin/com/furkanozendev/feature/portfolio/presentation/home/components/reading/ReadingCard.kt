package com.furkanozendev.feature.portfolio.presentation.home.components.reading

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.OpenInNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.furkanozendev.core.designsystem.colors.HomeColors
import com.furkanozendev.feature.portfolio.presentation.home.infra.LocalStringResources
import com.furkanozendev.feature.portfolio.presentation.home.widget.ReadingItem

@Composable
internal fun HighlightedReadingCard(
    item: ReadingItem,
    colors: HomeColors,
    onOpen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(colors.surfaceGlass)
            .border(1.dp, colors.borderPrimary, RoundedCornerShape(16.dp))
            .clickable(onClick = onOpen)
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .size(8.dp)
                        .background(colors.indicatorActive, CircleShape)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = item.source,
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = colors.textMuted,
                        fontWeight = FontWeight.SemiBold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.OpenInNew,
                    contentDescription = null,
                    tint = colors.textMuted,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = LocalStringResources.current["reading_external"],
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = colors.textMuted,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }

        Text(
            text = item.title,
            style = MaterialTheme.typography.titleSmall.copy(
                color = colors.textPrimary,
                fontWeight = FontWeight.SemiBold
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        if (item.tags.isNotEmpty()) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                item.tags.take(4).forEach { tag -> ReadingTag(tag, colors) }
                if (item.tags.size > 4) ReadingTag("+${item.tags.size - 4}", colors)
            }
        }
    }
}
