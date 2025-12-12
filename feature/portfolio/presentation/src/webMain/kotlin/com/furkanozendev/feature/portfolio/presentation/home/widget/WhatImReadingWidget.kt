package com.furkanozendev.feature.portfolio.presentation.home.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkanozendev.feature.portfolio.presentation.home.components.BentoCard


data class ReadingItem(
    val id: String,
    val title: String,
    val source: String,      // "Medium", "ProAndroidDev", "Kotlin Blog"
    val url: String,
    val tags: List<String> = emptyList()
)

data class KotlinReadingUiState(
    val todayItems: List<ReadingItem>,
    val highlightedIndex: Int = 0
)

@Composable
fun WhatImReadingWidget(
    modifier: Modifier = Modifier,
    uiState: KotlinReadingUiState = KotlinReadingUiState(
        todayItems = listOf(
            ReadingItem(
                id = "1",
                title = "Understanding Compose Multiplatform: Architecture & Patterns",
                source = "Medium · Kotlin/Compose",
                url = "https://medium.com/.../compose-mpp",
                tags = listOf("Compose", "KMP", "Architecture")
            ),
            ReadingItem(
                id = "2",
                title = "Kotlin Compiler Plugins: IR basics for real-world projects",
                source = "Blog · Kotlin",
                url = "https://...",
                tags = listOf("Compiler Plugins", "IR", "Tooling")
            )
        )
    ),
    onItemClick: (ReadingItem) -> Unit = { _ -> },
    onHighlightChanged: (Int) -> Unit = { _ -> }
) {
    val textPrimary = Color(0xFFE0E0E0)
    val textMuted = Color(0xFF9E9E9E)

    BentoCard(
        modifier = modifier,
        title = "Today I'm Reading",
        icon = Icons.Rounded.Book
    ) {
        if (uiState.todayItems.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "No articles added yet.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = textPrimary
                    )
                )
                Text(
                    text = "You can turn this into a daily Kotlin/engineering reading list.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = textMuted
                    )
                )
            }
            return@BentoCard
        }

        val uriHandler = LocalUriHandler.current
        val items = uiState.todayItems
        val index = uiState.highlightedIndex.coerceIn(0, items.lastIndex)
        val highlighted = items[index]

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Highlighted card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF181824))
                    .clickable {
                        onItemClick(highlighted)
                        uriHandler.openUri(highlighted.url)
                    }
                    .padding(14.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = highlighted.title,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = textPrimary,
                            fontWeight = FontWeight.SemiBold
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = highlighted.source,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = textMuted
                        )
                    )

                    if (highlighted.tags.isNotEmpty()) {
                        FlowRow(
                            modifier = Modifier.padding(top = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            highlighted.tags.forEach { tag ->
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(999.dp))
                                        .background(Color(0x22FFFFFF))
                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                ) {
                                    Text(
                                        text = tag,
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Color(0xFFE0E0E0),
                                            fontSize = 11.sp
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Slider / pager controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Daily Kotlin / dev reading",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = textMuted
                    )
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            val newIndex = (index - 1).coerceAtLeast(0)
                            onHighlightChanged(newIndex)
                        },
                        enabled = index > 0
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ChevronLeft,
                            contentDescription = "Previous article",
                            tint = if (index > 0) textPrimary else textMuted
                        )
                    }

                    Text(
                        text = "${index + 1}/${items.size}",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = textMuted
                        )
                    )

                    IconButton(
                        onClick = {
                            val newIndex = (index + 1).coerceAtMost(items.lastIndex)
                            onHighlightChanged(newIndex)
                        },
                        enabled = index < items.lastIndex
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ChevronRight,
                            contentDescription = "Next article",
                            tint = if (index < items.lastIndex) textPrimary else textMuted
                        )
                    }
                }
            }
        }
    }
}

