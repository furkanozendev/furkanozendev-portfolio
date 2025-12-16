package com.furkanozendev.feature.portfolio.presentation.home.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Work
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkanozendev.core.designsystem.components.SectionHeader
import com.furkanozendev.feature.portfolio.presentation.home.components.BentoCard
import furkanozendev.feature.portfolio.presentation.generated.resources.Res
import furkanozendev.feature.portfolio.presentation.generated.resources.papara_logo
import kotlinx.atomicfu.TraceBase.None.append
import org.jetbrains.compose.resources.painterResource

@Immutable
data class ExperienceSection(
    val heading: String,
    val highlight: String?,
    val bullets: List<String> = emptyList()
)


private val androidDevSections = listOf(
    ExperienceSection(
        heading = "Product Engineering",
        highlight = "Owned critical user flows (*Card*, *Investment*, *Onboarding*) with focus on high-performance UI."
    ),
    ExperienceSection(
        heading = "Accessibility & Innovation",
        highlight = "Architected 'SketchMyCard' (*Canvas engine*) and 'VoiceCard' (*Bluetooth/TTS*) for accessibility."
    ),
    ExperienceSection(
        heading = "Architecture & Platform",
        highlight = "Led migration to *Jetpack Compose*, establishing a reusable *Design System* and modular boundaries."
    ),
    ExperienceSection(
        heading = "Testing & Tooling",
        highlight = "Elevated quality via *Maestro E2E*, unit testing culture, and internal *CLI tooling*."
    )
)

private val androidDevKeyWins = listOf(
    "Sole owner of accessibility-driven features used in production card flows.",
    "Designed Compose-based UI foundations reused across multiple product areas.",
    "Improved long-term maintainability by enforcing modularization and architectural consistency during platform migration."
)

private val internSections = ExperienceSection(
    heading = "Internship",
    highlight = "Contributed to onboarding/KYC improvements and assisted early-stage architectural migrations."
)

@Immutable
private data class ExperienceSpec(
    val contentPadding: PaddingValues,
    val itemGap: Dp,
    val maxTextWidth: Dp,
    val showTimeline: Boolean
)

@Composable
private fun rememberExperienceSpec(maxWidth: Dp): ExperienceSpec {
    val compact = maxWidth < 520.dp
    val wide = maxWidth >= 820.dp

    return remember(maxWidth) {
        ExperienceSpec(
            contentPadding = when {
                wide -> PaddingValues(28.dp)
                compact -> PaddingValues(18.dp)
                else -> PaddingValues(22.dp)
            },
            itemGap = if (compact) 14.dp else 18.dp,
            maxTextWidth = if (wide) 780.dp else Dp.Infinity,
            showTimeline = !compact
        )
    }
}

@Composable
fun ExperienceWidget(modifier: Modifier = Modifier) {
    BentoCard(
        modifier = modifier,
        title = "Experience",
        icon = Icons.Rounded.Work
    ) {
        BoxWithConstraints {
            val spec = rememberExperienceSpec(maxWidth)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spec.contentPadding),
                verticalArrangement = Arrangement.spacedBy(spec.itemGap)
            ) {
                SectionHeader(
                    title = "Professional experience",
                    subtitle = "Hands-on product engineering across fintech domains, accessibility features, and platform modernization.",
                    textPrimary = Color(0xFFEDEDED),
                    textMuted = Color(0xFFB0B0B0)
                )

                CompanyHeaderRow(
                    logo = painterResource(Res.drawable.papara_logo),
                    company = "Papara",
                    period = "Feb 2022 — Dec 2025 · 3 yrs 10 mos"
                )

                ExperienceRoleItem(
                    title = "Android Developer",
                    date = "Apr 2022 — Dec 2025",
                    subtitle = "Contributed to major product areas and helped modernize the engineering foundation of one of Turkey’s largest fintech mobile apps.",
                    sections = androidDevSections,
                    keyWins = androidDevKeyWins,
                    spec = spec,
                    showTimeline = spec.showTimeline,
                    isLast = false
                )

                ExperienceInternItem(
                    title = "Android Developer Intern",
                    date = "Feb 2022 — Apr 2022",
                    section = internSections,
                    spec = spec,
                    showTimeline = spec.showTimeline,
                    isLast = true
                )
            }
        }
    }
}

@Composable
private fun CompanyHeaderRow(
    logo: Painter,
    company: String,
    period: String
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
                    color = Color.White
                )
            )
            Text(
                period,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFFBDBDBD)
                )
            )
        }
    }
}

@Composable
private fun ExperienceRoleItem(
    title: String,
    date: String,
    subtitle: String? = null,
    sections: List<ExperienceSection>,
    keyWins: List<String>,
    spec: ExperienceSpec,
    showTimeline: Boolean,
    isLast: Boolean
) {
    val compact = spec.maxTextWidth == Dp.Infinity

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        if (showTimeline) TimelineRail(isLast = isLast)

        Column(
            modifier = Modifier
                .widthIn(max = spec.maxTextWidth)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            RoleHeader(title = title, date = date, subtitle = subtitle)

            FocusAreasGrid(
                sections = sections,
                compact = compact
            )

            KeyWinsBlock(
                bullets = keyWins,
                compact = compact
            )
        }
    }
}

@Composable
private fun KeyWinsBlock(
    bullets: List<String>,
    compact: Boolean
) {
    if (bullets.isEmpty()) return

    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = "Engineering impact",
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color(0xFFB0B0B0),
                fontWeight = FontWeight.SemiBold
            )
        )

        bullets.take(if (compact) 2 else 3).forEach { bullet ->
            BulletText(bullet)
        }
    }
}

@Composable
private fun FocusPill(
    heading: String,
    highlight: String,
    modifier: Modifier = Modifier
) {
    val accentColor = Color(0xFF8BE9FD)

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF1E1E1E))
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.1f),
                        Color.White.copy(alpha = 0.02f)
                    )
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = heading.uppercase(),
            style = MaterialTheme.typography.labelSmall.copy(
                color = accentColor.copy(alpha = 0.9f),
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        )

        Text(
            text = highlightKeywords(highlight, highlightColor = Color(0xFFE0E0E0)),
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color(0xFFAAAAAA),
                lineHeight = 16.sp
            ),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun ExperienceInternItem(
    title: String,
    date: String,
    section: ExperienceSection,
    spec: ExperienceSpec,
    showTimeline: Boolean,
    isLast: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        if (showTimeline) TimelineRail(isLast = isLast)

        Column(
            modifier = Modifier
                .widthIn(max = spec.maxTextWidth)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RoleHeader(title = title, date = date, subtitle = section.highlight)
        }
    }
}

@Composable
private fun RoleHeader(
    title: String,
    date: String,
    subtitle: String?
) {
    Text(
        title,
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    )
    Text(
        date,
        style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFFB0B0B0))
    )

    if (!subtitle.isNullOrBlank()) {
        Text(
            subtitle,
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color(0xFFD8D8D8),
                lineHeight = 18.sp
            )
        )
    }
}

@Composable
private fun FocusAreasGrid(
    sections: List<ExperienceSection>,
    compact: Boolean
) {
    val cols = if (compact) 1 else 2

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "KEY FOCUS AREAS",
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color(0xFFB0B0B0),
                fontWeight = FontWeight.SemiBold
            )
        )

        val rows = sections.chunked(cols)
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            rows.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    row.forEach { section ->
                        FocusPill(
                            heading = section.heading,
                            highlight = section.highlight.orEmpty(),
                            modifier = Modifier.weight(1f)
                        )
                    }
                    repeat(cols - row.size) {
                        Spacer(Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun BulletText(text: String) {
    Text(
        text = "• $text",
        style = MaterialTheme.typography.bodySmall.copy(
            color = Color(0xFFD8D8D8),
            lineHeight = 18.sp
        )
    )
}

@Composable
private fun TimelineRail(isLast: Boolean) {
    Column(
        modifier = Modifier
            .width(18.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .size(10.dp)
                .background(Color(0xFF6B6B6B), CircleShape)
        )
        if (!isLast) {
            Spacer(Modifier.height(6.dp))
            Box(
                Modifier
                    .width(2.dp)
                    .fillMaxHeight()
                    .background(Color.White.copy(alpha = 0.14f))
            )
        }
    }
}

@Composable
fun highlightKeywords(text: String, highlightColor: Color = Color(0xFF4DB6AC)): AnnotatedString {
    return buildAnnotatedString {
        val parts = text.split("*")
        parts.forEachIndexed { index, part ->
            if (index % 2 == 1) {
                withStyle(
                    style = SpanStyle(
                        color = highlightColor,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(part)
                }
            } else {
                append(part)
            }
        }
    }
}
