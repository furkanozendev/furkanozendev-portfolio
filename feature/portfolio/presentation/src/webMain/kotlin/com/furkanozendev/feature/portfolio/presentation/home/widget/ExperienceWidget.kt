package com.furkanozendev.feature.portfolio.presentation.home.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkanozendev.core.designsystem.components.SectionHeader
import com.furkanozendev.feature.portfolio.presentation.home.components.BentoCard
import furkanozendev.feature.portfolio.presentation.generated.resources.Res
import furkanozendev.feature.portfolio.presentation.generated.resources.papara_logo
import org.jetbrains.compose.resources.painterResource

@Immutable
data class ExperienceSection(
    val heading: String,
    val bullets: List<String>
)

private val androidDevSections = listOf(
    ExperienceSection(
        heading = "Product Engineering",
        bullets = listOf(
            "Worked across Cards, Cashback, Onboarding, and Investments—delivering user-facing features and improving performance and reliability.",
            "Collaborated closely with product, backend, and design teams."
        )
    ),
    ExperienceSection(
        heading = "Accessibility & Innovation",
        bullets = listOf(
            "Developed VoiceCard using Bluetooth + Text-to-Speech to assist visually impaired users during card transactions.",
            "Built SketchMyCard, a custom Android Canvas drawing/editor to design personalized physical cards."
        )
    ),
    ExperienceSection(
        heading = "Architecture & Platform Modernization",
        bullets = listOf(
            "Contributed to migration to Jetpack Compose by building reusable UI components and setting modern patterns.",
            "Participated in modularization, Clean Architecture adoption, and refactoring across key product areas."
        )
    ),
    ExperienceSection(
        heading = "Testing & Tooling",
        bullets = listOf(
            "Improved reliability with unit tests (JUnit, MockK) and automated E2E flows using Maestro.",
            "Supported code reviews, internal tooling enhancements, and engineering best practices."
        )
    )
)

private val internSections = listOf(
    ExperienceSection(
        heading = "Internship",
        bullets = listOf(
            "Contributed improvements to onboarding/KYC flows and assisted architectural migrations.",
            "Demonstrated rapid growth and transitioned into full-time engineering within months."
        )
    )
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
            showTimeline = !compact // timeline looks cramped on mobile widths
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
                    spec = spec,
                    showTimeline = spec.showTimeline,
                    isLast = false
                )

                ExperienceRoleItem(
                    title = "Android Developer Intern",
                    date = "Feb 2022 — Apr 2022",
                    sections = internSections,
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
    spec: ExperienceSpec,
    showTimeline: Boolean,
    isLast: Boolean
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        if (showTimeline) {
            TimelineRail(isLast = isLast)
        }

        Column(
            modifier = Modifier
                .widthIn(max = spec.maxTextWidth)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            )
            Text(
                date,
                style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFFB0B0B0))
            )

            if (subtitle != null) {
                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFFD8D8D8),
                        lineHeight = 18.sp
                    )
                )
            }

            // Highlights (always visible) — pick first bullet from each section
            HighlightsBlock(sections = sections)

            // Expand / collapse details
            ExpandToggle(expanded = expanded, onToggle = { expanded = !expanded })

            AnimatedVisibility(visible = expanded) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    sections.forEach { section ->
                        SectionBlock(section)
                    }
                }
            }
        }
    }
}

@Composable
private fun TimelineRail(isLast: Boolean) {
    Column(
        modifier = Modifier.width(18.dp),
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
                    .height(140.dp) // fixed rail segment (good enough visually)
                    .background(Color.White.copy(alpha = 0.14f))
            )
        }
    }
}

@Composable
private fun HighlightsBlock(sections: List<ExperienceSection>) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.padding(top = 6.dp)) {
        sections.take(3).forEach { section ->
            val highlight = section.bullets.firstOrNull() ?: return@forEach
            Text(
                text = "• ${section.heading}: $highlight",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xFFE0E0E0),
                    lineHeight = 18.sp
                )
            )
        }
    }
}

@Composable
private fun SectionBlock(section: ExperienceSection) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            section.heading,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = Color.White.copy(alpha = 0.95f)
            )
        )
        section.bullets.forEach { bullet ->
            Text(
                text = "• $bullet",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xFFD8D8D8),
                    lineHeight = 18.sp
                )
            )
        }
    }
}

@Composable
private fun ExpandToggle(expanded: Boolean, onToggle: () -> Unit) {
    Text(
        text = if (expanded) "Hide details" else "Show details",
        modifier = Modifier
            .padding(top = 4.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White.copy(alpha = 0.06f))
            .clickable(onClick = onToggle)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        style = MaterialTheme.typography.bodySmall.copy(
            color = Color(0xFF8BE9FD),
            fontWeight = FontWeight.Medium
        )
    )
}
