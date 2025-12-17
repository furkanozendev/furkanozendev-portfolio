package com.furkanozendev.feature.portfolio.presentation.home.widget.whatImReading

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkanozendev.core.designsystem.colors.HomeColors
import com.furkanozendev.core.designsystem.colors.LocalHomeColors
import com.furkanozendev.core.designsystem.components.SectionHeader
import com.furkanozendev.feature.portfolio.presentation.home.components.BentoCard
import com.furkanozendev.feature.portfolio.presentation.home.components.reading.HighlightedReadingCard
import com.furkanozendev.feature.portfolio.presentation.home.components.reading.ReadingShowMoreRow
import org.koin.compose.viewmodel.koinViewModel

data class ReadingItem(
    val id: String,
    val title: String,
    val source: String,
    val url: String,
    val tags: List<String> = emptyList()
)

data class KotlinReadingUiState(
    val todayItems: List<ReadingItem>
)

@Composable
fun WhatImReadingWidget(
    modifier: Modifier = Modifier,
    viewModel: WhatImReadingViewModel = koinViewModel(),
    initiallyExpanded: Boolean = false,
    initialVisibleCount: Int = 3
) {
    val uiState by viewModel.uiState.collectAsState()
    val uriHandler = LocalUriHandler.current
    var expanded by remember { mutableStateOf(initiallyExpanded) }
    val colors = LocalHomeColors.current

    BentoCard(
        modifier = modifier,
        title = uiState.title,
        icon = Icons.Rounded.Book
    ) {
        val items = uiState.items.map {
            ReadingItem(
                id = it.id,
                title = it.title,
                source = it.source,
                url = it.url,
                tags = it.tags
            )
        }

        if (items.isEmpty()) {
            EmptyReadingState(colors = colors, title = uiState.emptyTitle, subtitle = uiState.emptySubtitle)
            return@BentoCard
        }

        val safeInitial = initialVisibleCount.coerceIn(1, 6)
        val visibleCount = if (expanded) items.size else minOf(items.size, safeInitial)
        val visibleItems = items.take(visibleCount)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
                .animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SectionHeader(
                title = uiState.title,
                subtitle = uiState.headerSubtitle,
                textPrimary = colors.textPrimary,
                textMuted = colors.textMuted
            )

            visibleItems.forEach { item ->
                HighlightedReadingCard(
                    item = item,
                    colors = colors,
                    onOpen = { uriHandler.openUri(item.url) }
                )
            }

            val remaining = items.size - visibleCount
            if (items.size > safeInitial) {
                ReadingShowMoreRow(
                    expanded = expanded,
                    remainingCount = remaining.coerceAtLeast(0),
                    onToggle = { expanded = !expanded },
                    colors = colors
                )
            }
        }
    }
}

@Composable
private fun EmptyReadingState(colors: HomeColors, title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                color = colors.textPrimary,
                fontWeight = FontWeight.SemiBold
            )
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall.copy(
                color = colors.textMuted,
                lineHeight = 18.sp
            )
        )
    }
}
