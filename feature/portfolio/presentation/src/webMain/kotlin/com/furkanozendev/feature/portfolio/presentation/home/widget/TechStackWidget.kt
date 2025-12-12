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
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkanozendev.feature.portfolio.presentation.home.components.BentoCard

@Composable
fun TechStackWidget(
    modifier: Modifier = Modifier
) {
    val accent1 = Color(0xFFBD93F9)
    val accent2 = Color(0xFF8BE9FD)
    val textPrimary = Color(0xFFE0E0E0)
    val textMuted = Color(0xFF9E9E9E)

    BentoCard(
        modifier = modifier,
        title = "Tech Stack",
        icon = Icons.Rounded.Code
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .padding(24.dp)
        ) {
            val isWide = maxWidth > 700.dp

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header / tagline
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (isWide) Arrangement.SpaceBetween else Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "End-to-end Kotlin-focused builder",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = textPrimary,
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    if (isWide) {
                        TechHighlightPill(
                            text = "Android · KMP · Backend · Tooling",
                            accent1 = accent1,
                            accent2 = accent2
                        )
                    }
                }

                if (!isWide) {
                    TechHighlightPill(
                        text = "Android · KMP · Backend · Tooling",
                        accent1 = accent1,
                        accent2 = accent2
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Content layout
                if (isWide) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            TechCategory(
                                title = "Core",
                                subtitle = "Languages & foundations",
                                items = listOf(
                                    "Kotlin",
                                    "SQL · PostgreSQL",
                                    "Coroutines · Flow"
                                ),
                                textPrimary = textPrimary,
                                textMuted = textMuted,
                                accent = accent1
                            )

                            TechCategory(
                                title = "Android & KMP",
                                subtitle = "Apps, UI and architecture",
                                items = listOf(
                                    "Jetpack Compose",
                                    "Compose Multiplatform",
                                    "Kotlin Multiplatform",
                                    "Clean Architecture · MVVM",
                                    "Hilt · Koin",
                                    "Unidirectional data flow"
                                ),
                                textPrimary = textPrimary,
                                textMuted = textMuted,
                                accent = accent2
                            )
                        }

                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            TechCategory(
                                title = "Backend & Infra",
                                subtitle = "APIs and supporting services",
                                items = listOf(
                                    "Ktor",
                                    "Supabase · Postgres",
                                    "REST · WebSocket APIs"
                                ),
                                textPrimary = textPrimary,
                                textMuted = textMuted,
                                accent = accent1
                            )

                            TechCategory(
                                title = "Tooling",
                                subtitle = "Developer experience",
                                items = listOf(
                                    "Kotlin compiler plugins (IR)",
                                    "KSP · Annotation processing",
                                    "Gradle convention plugins",
                                    "Analytics SDKs"
                                ),
                                textPrimary = textPrimary,
                                textMuted = textMuted,
                                accent = accent2
                            )

                            TechCategory(
                                title = "Platforms & Tools",
                                subtitle = "Daily drivers",
                                items = listOf(
                                    "Android Studio · IntelliJ",
                                    "Xcode · iOS targets",
                                    "Git · GitHub",
                                    "Docker · CI/CD (GitHub Actions)"
                                ),
                                textPrimary = textPrimary,
                                textMuted = textMuted,
                                accent = accent1
                            )
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        TechCategory(
                            title = "Core",
                            subtitle = "Languages & foundations",
                            items = listOf(
                                "Kotlin",
                                "SQL · PostgreSQL",
                                "Coroutines · Flow"
                            ),
                            textPrimary = textPrimary,
                            textMuted = textMuted,
                            accent = accent1
                        )

                        TechCategory(
                            title = "Android & KMP",
                            subtitle = "Apps, UI and architecture",
                            items = listOf(
                                "Jetpack Compose",
                                "Compose Multiplatform",
                                "Kotlin Multiplatform",
                                "Clean Architecture · MVVM",
                                "Hilt · Koin",
                                "Unidirectional data flow"
                            ),
                            textPrimary = textPrimary,
                            textMuted = textMuted,
                            accent = accent2
                        )

                        TechCategory(
                            title = "Backend & Infra",
                            subtitle = "APIs and supporting services",
                            items = listOf(
                                "Ktor",
                                "Supabase · Postgres",
                                "REST · WebSocket APIs"
                            ),
                            textPrimary = textPrimary,
                            textMuted = textMuted,
                            accent = accent1
                        )

                        TechCategory(
                            title = "Tooling",
                            subtitle = "Developer experience",
                            items = listOf(
                                "Kotlin compiler plugins (IR)",
                                "KSP · Annotation processing",
                                "Gradle convention plugins",
                                "Analytics SDKs"
                            ),
                            textPrimary = textPrimary,
                            textMuted = textMuted,
                            accent = accent2
                        )

                        TechCategory(
                            title = "Platforms & Tools",
                            subtitle = "Daily drivers",
                            items = listOf(
                                "Android Studio · IntelliJ",
                                "Xcode · iOS targets",
                                "Git · GitHub",
                                "Docker · CI/CD (GitHub Actions)"
                            ),
                            textPrimary = textPrimary,
                            textMuted = textMuted,
                            accent = accent1
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TechCategory(
    title: String,
    subtitle: String,
    items: List<String>,
    textPrimary: Color,
    textMuted: Color,
    accent: Color
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(20.dp)
                    .background(accent)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = textPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = textMuted
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items.forEach { item ->
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
            .background(Color(0x22FFFFFF))
            .border(
                width = 1.dp,
                color = Color(0x33FFFFFF),
                shape = RoundedCornerShape(999.dp)
            )
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color(0xFFE0E0E0),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
private fun TechHighlightPill(
    text: String,
    accent1: Color,
    accent2: Color
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        accent1.copy(alpha = 0.35f),
                        accent2.copy(alpha = 0.35f)
                    )
                )
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color(0xFFF5F5F5),
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}
