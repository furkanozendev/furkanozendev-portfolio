package com.furkanozendev.feature.portfolio.presentation.home.widget

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
import com.furkanozendev.feature.portfolio.presentation.home.infra.LocalStringResources

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
    items: List<ReadingItem> = sampleReadingItems(),
    initiallyExpanded: Boolean = false,
    initialVisibleCount: Int = 3
) {
    val uriHandler = LocalUriHandler.current
    var expanded by remember(items) { mutableStateOf(initiallyExpanded) }

    WhatImReadingWidgetContent(
        modifier = modifier,
        uiState = KotlinReadingUiState(todayItems = items),
        expanded = expanded,
        initialVisibleCount = initialVisibleCount,
        onToggleExpanded = { expanded = !expanded },
        onOpen = { uriHandler.openUri(it.url) }
    )
}

@Composable
private fun sampleReadingItems() = listOf(
    ReadingItem(
        id = "1",
        title = "Understanding Compose Multiplatform: Architecture & Patterns",
        source = "Medium · Kotlin/Compose",
        url = "https://medium.com/...",
        tags = listOf("Compose", "KMP", "Architecture")
    ),
    ReadingItem(
        id = "2",
        title = "Kotlin Compiler Plugins: IR basics for real-world projects",
        source = "Blog · Kotlin",
        url = "https://...",
        tags = listOf("Compiler Plugins", "IR", "Tooling")
    ),
    ReadingItem(
        id = "3",
        title = "Kotlin Compiler Plugins: IR basics for real-world projects",
        source = "Blog · Kotlin",
        url = "https://...",
        tags = listOf("Compiler Plugins", "IR", "Tooling")
    ),
    ReadingItem(
        id = "4",
        title = "Kotlin Compiler Plugins: IR basics for real-world projects",
        source = "Blog · Kotlin",
        url = "https://...",
        tags = listOf("Compiler Plugins", "IR", "Tooling")
    ),
    ReadingItem(
        id = "5",
        title = "Kotlin Compiler Plugins: IR basics for real-world projects",
        source = "Blog · Kotlin",
        url = "https://...",
        tags = listOf("Compiler Plugins", "IR", "Tooling")
    ),
    ReadingItem(
        id = "6",
        title = "Kotlin Compiler Plugins: IR basics for real-world projects",
        source = "Blog · Kotlin",
        url = "https://...",
        tags = listOf("Compiler Plugins", "IR", "Tooling")
    ),
)

@Composable
fun WhatImReadingWidgetContent(
    modifier: Modifier = Modifier,
    uiState: KotlinReadingUiState,
    expanded: Boolean,
    initialVisibleCount: Int = 3,
    onToggleExpanded: () -> Unit,
    onOpen: (ReadingItem) -> Unit
) {
    val colors = LocalHomeColors.current

    BentoCard(
        modifier = modifier,
        title = LocalStringResources.current["reading_title"],
        icon = Icons.Rounded.Book
    ) {
        val items = uiState.todayItems

        if (items.isEmpty()) {
            EmptyReadingState(colors)
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
                title = LocalStringResources.current["reading_title"],
                subtitle = LocalStringResources.current["reading_header_subtitle"],
                textPrimary = colors.textPrimary,
                textMuted = colors.textMuted
            )

            visibleItems.forEach { item ->
                HighlightedReadingCard(
                    item = item,
                    colors = colors,
                    onOpen = { onOpen(item) }
                )
            }

            val remaining = items.size - visibleCount
            if (items.size > safeInitial) {
                ReadingShowMoreRow(
                    expanded = expanded,
                    remainingCount = remaining.coerceAtLeast(0),
                    onToggle = onToggleExpanded,
                    colors = colors
                )
            }
        }
    }
}

@Composable
private fun EmptyReadingState(colors: HomeColors) {
    val res = LocalStringResources.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = res["reading_empty_title"],
            style = MaterialTheme.typography.titleSmall.copy(
                color = colors.textPrimary,
                fontWeight = FontWeight.SemiBold
            )
        )
        Text(
            text = res["reading_empty_subtitle"],
            style = MaterialTheme.typography.bodySmall.copy(
                color = colors.textMuted,
                lineHeight = 18.sp
            )
        )
    }
}
