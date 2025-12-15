package com.furkanozendev.feature.portfolio.presentation.home.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkanozendev.core.designsystem.components.SectionHeader
import com.furkanozendev.feature.portfolio.presentation.home.components.BentoCard

@Immutable
private data class TechCategoryUi(
    val title: String,
    val subtitle: String,
    val emphasis: Emphasis,
    val items: List<String>
)

private enum class Emphasis { Primary, Secondary }

private val techCategories = listOf(
    TechCategoryUi(
        title = "Android",
        subtitle = "Shipped production features & UI systems",
        emphasis = Emphasis.Primary,
        items = listOf(
            "Kotlin", "Jetpack Compose", "Coroutines", "Flow",
            "Clean Architecture", "MVVM", "Hilt", "Testing (JUnit/MockK)"
        )
    ),
    TechCategoryUi(
        title = "Multiplatform",
        subtitle = "Cross-platform UI & shared logic",
        emphasis = Emphasis.Secondary,
        items = listOf(
            "Compose Multiplatform", "Kotlin Multiplatform", "Koin", "iOS targets"
        )
    ),
    TechCategoryUi(
        title = "Backend",
        subtitle = "APIs that support products",
        emphasis = Emphasis.Secondary,
        items = listOf(
            "Ktor", "REST", "WebSockets", "PostgreSQL", "Supabase"
        )
    ),
    TechCategoryUi(
        title = "Tooling",
        subtitle = "DX, build systems, and metaprogramming",
        emphasis = Emphasis.Secondary,
        items = listOf(
            "Compiler Plugins (IR)", "KSP", "Gradle Convention Plugins", "Analytics SDKs"
        )
    )
)

@Composable
private fun ResponsiveColumns(
    modifier: Modifier = Modifier,
    columns: Int,
    gap: Dp,
    content: @Composable (columnIndex: Int) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(gap)
    ) {
        repeat(columns) { i ->
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(gap)
            ) { content(i) }
        }
    }
}

@Composable
fun TechStackWidget(modifier: Modifier = Modifier) {
    val accentPrimary = Color(0xFF8BE9FD)   // cyan
    val accentSecondary = Color(0xFFBD93F9) // purple
    val textPrimary = Color(0xFFEDEDED)
    val textMuted = Color(0xFFB0B0B0)

    BentoCard(
        modifier = modifier,
        title = "Tech Stack",
        icon = Icons.Rounded.Code
    ) {
        BoxWithConstraints(Modifier.fillMaxWidth()) {
            val isCompact = maxWidth < 520.dp
            val isWide = maxWidth >= 900.dp

            val padding = when {
                isWide -> 28.dp
                isCompact -> 18.dp
                else -> 22.dp
            }
            val gap = if (isCompact) 14.dp else 16.dp
            val cols = if (isWide) 2 else 1

            val (primary, secondary) = techCategories.partition { it.emphasis == Emphasis.Primary }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(gap)
            ) {
                SectionHeader(
                    title = "Kotlin-first engineer building product UI, architecture, and tooling.",
                    subtitle = "Strongest in Android + Compose; experienced with KMP, backend support, and developer tooling.",
                    textPrimary = textPrimary,
                    textMuted = textMuted
                )

                Text(
                    text = "Primary",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = textMuted,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                ResponsiveColumns(columns = cols, gap = gap) { col ->
                    val items = primary.chunked((primary.size + cols - 1) / cols).getOrNull(col).orEmpty()
                    items.forEach { cat ->
                        TechCategoryCard(
                            category = cat,
                            accent = accentPrimary,
                            textPrimary = textPrimary,
                            textMuted = textMuted
                        )
                    }
                }

                Text(
                    text = "Secondary",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = textMuted,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                ResponsiveColumns(columns = cols, gap = gap) { col ->
                    val items = secondary.chunked((secondary.size + cols - 1) / cols).getOrNull(col).orEmpty()
                    items.forEach { cat ->
                        TechCategoryCard(
                            category = cat,
                            accent = accentSecondary,
                            textPrimary = textPrimary,
                            textMuted = textMuted
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TechCategoryCard(
    category: TechCategoryUi,
    accent: Color,
    textPrimary: Color,
    textMuted: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White.copy(alpha = 0.04f))
            .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .width(3.dp)
                    .height(18.dp)
                    .background(accent, RoundedCornerShape(2.dp))
            )
            Spacer(Modifier.width(10.dp))
            Column {
                Text(
                    text = category.title,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = textPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = category.subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = textMuted,
                        lineHeight = 18.sp
                    )
                )
            }
        }

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            category.items.take(10).forEach { item ->
                TechTagChip(text = item)
            }
        }
    }
}

@Composable
private fun TechTagChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(Color.White.copy(alpha = 0.06f))
            .border(1.dp, Color.White.copy(alpha = 0.10f), RoundedCornerShape(999.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color(0xFFEDEDED),
                fontWeight = FontWeight.Medium
            )
        )
    }
}
