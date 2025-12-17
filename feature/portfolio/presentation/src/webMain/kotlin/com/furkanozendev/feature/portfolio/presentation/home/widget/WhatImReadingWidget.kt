package com.furkanozendev.feature.portfolio.presentation.home.widget

import androidx.compose.animation.animateContentSize
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
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkanozendev.core.designsystem.components.SectionHeader
import com.furkanozendev.feature.portfolio.presentation.home.components.BentoCard
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
    val textPrimary = Color(0xFFEDEDED)
    val textMuted = Color(0xFFB0B0B0)
    val surface = Color.White.copy(alpha = 0.04f)

    BentoCard(
        modifier = modifier,
        title = LocalStringResources.current["reading_title"],
        icon = Icons.Rounded.Book
    ) {
        val items = uiState.todayItems

        if (items.isEmpty()) {
            EmptyReadingState(textPrimary, textMuted)
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
                textPrimary = textPrimary,
                textMuted = textMuted
            )

            visibleItems.forEach { item ->
                HighlightedReadingCard(
                    item = item,
                    surface = surface,
                    textPrimary = textPrimary,
                    textMuted = textMuted,
                    onOpen = { onOpen(item) }
                )
            }

            val remaining = items.size - visibleCount
            if (items.size > safeInitial) {
                ReadingShowMoreRow(
                    expanded = expanded,
                    remainingCount = remaining.coerceAtLeast(0),
                    onToggle = onToggleExpanded,
                    textMuted = textMuted
                )
            }
        }
    }
}

@Composable
private fun HighlightedReadingCard(
    item: ReadingItem,
    surface: Color,
    textPrimary: Color,
    textMuted: Color,
    onOpen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(surface)
            .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
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
                        .background(Color(0xFF8BE9FD).copy(alpha = 0.8f), CircleShape)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = item.source,
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = textMuted,
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
                    tint = textMuted,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = LocalStringResources.current["reading_external"],
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = textMuted,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }

        Text(
            text = item.title,
            style = MaterialTheme.typography.titleSmall.copy(
                color = textPrimary,
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
                item.tags.take(4).forEach { tag -> ReadingTag(tag) }
                if (item.tags.size > 4) ReadingTag("+${item.tags.size - 4}")
            }
        }
    }
}

@Composable
private fun ReadingTag(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(Color.White.copy(alpha = 0.06f))
            .border(1.dp, Color.White.copy(alpha = 0.10f), RoundedCornerShape(999.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                color = Color(0xFFEDEDED)
            )
        )
    }
}

@Composable
private fun EmptyReadingState(textPrimary: Color, textMuted: Color) {
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
                color = textPrimary,
                fontWeight = FontWeight.SemiBold
            )
        )
        Text(
            text = res["reading_empty_subtitle"],
            style = MaterialTheme.typography.bodySmall.copy(
                color = textMuted,
                lineHeight = 18.sp
            )
        )
    }
}

@Composable
private fun ReadingShowMoreRow(
    expanded: Boolean,
    remainingCount: Int,
    onToggle: () -> Unit,
    textMuted: Color
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
                .background(Color.White.copy(alpha = 0.05f))
                .border(1.dp, Color.White.copy(alpha = 0.10f), RoundedCornerShape(999.dp))
                .clickable(onClick = onToggle)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium.copy(
                    color = textMuted,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}
