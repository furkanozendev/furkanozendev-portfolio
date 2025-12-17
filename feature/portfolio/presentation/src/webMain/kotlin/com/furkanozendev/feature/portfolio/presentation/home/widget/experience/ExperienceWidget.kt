package com.furkanozendev.feature.portfolio.presentation.home.widget.experience

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Work
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.furkanozendev.core.designsystem.colors.HomeColors
import com.furkanozendev.core.designsystem.colors.LocalHomeColors
import com.furkanozendev.core.designsystem.components.SectionHeader
import com.furkanozendev.feature.portfolio.presentation.home.components.BentoCard
import com.furkanozendev.feature.portfolio.presentation.home.components.experience.BulletText
import com.furkanozendev.feature.portfolio.presentation.home.components.experience.CompanyHeaderRow
import com.furkanozendev.feature.portfolio.presentation.home.components.experience.FocusPill
import com.furkanozendev.feature.portfolio.presentation.home.components.experience.RoleHeader
import com.furkanozendev.feature.portfolio.presentation.home.components.experience.TimelineRail
import furkanozendev.feature.portfolio.presentation.generated.resources.Res
import furkanozendev.feature.portfolio.presentation.generated.resources.papara_logo
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Immutable
data class ExperienceSection(
    val heading: String,
    val highlight: String?,
    val bullets: List<String> = emptyList()
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
fun ExperienceWidget(
    modifier: Modifier = Modifier,
    viewModel: ExperienceViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val colors = LocalHomeColors.current

    BentoCard(
        modifier = modifier,
        title = uiState.title,
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
                    title = uiState.headerTitle,
                    subtitle = uiState.headerSubtitle,
                    textPrimary = colors.textPrimary,
                    textMuted = colors.textMuted
                )

                CompanyHeaderRow(
                    logo = painterResource(Res.drawable.papara_logo),
                    company = uiState.company,
                    period = uiState.companyPeriod,
                    colors = colors
                )

                uiState.roles.forEachIndexed { index, role ->
                    val sections = role.sections.map {
                        ExperienceSection(
                            heading = it.heading,
                            highlight = it.highlight,
                            bullets = it.bullets
                        )
                    }

                    if (role.isIntern) {
                        ExperienceInternItem(
                            title = role.title,
                            date = role.date,
                            section = sections.firstOrNull() ?: ExperienceSection("", ""),
                            spec = spec,
                            showTimeline = spec.showTimeline,
                            isLast = index == uiState.roles.lastIndex,
                            colors = colors
                        )
                    } else {
                        ExperienceRoleItem(
                            title = role.title,
                            date = role.date,
                            subtitle = role.subtitle,
                            sections = sections,
                            keyWins = role.keyWins,
                            keyWinsTitle = role.keyWinsTitle,
                            focusAreasLabel = role.focusAreasLabel,
                            spec = spec,
                            showTimeline = spec.showTimeline,
                            isLast = index == uiState.roles.lastIndex,
                            colors = colors
                        )
                    }
                }
            }
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
    keyWinsTitle: String,
    focusAreasLabel: String,
    spec: ExperienceSpec,
    showTimeline: Boolean,
    isLast: Boolean,
    colors: HomeColors
) {
    val compact = spec.maxTextWidth == Dp.Infinity

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        if (showTimeline) TimelineRail(isLast = isLast, colors = colors)

        Column(
            modifier = Modifier
                .widthIn(max = spec.maxTextWidth)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            RoleHeader(title = title, date = date, subtitle = subtitle, colors = colors)

            FocusAreasGrid(
                sections = sections,
                compact = compact,
                label = focusAreasLabel,
                colors = colors
            )

            KeyWinsBlock(
                bullets = keyWins,
                compact = compact,
                title = keyWinsTitle,
                colors = colors
            )
        }
    }
}

@Composable
private fun KeyWinsBlock(
    bullets: List<String>,
    compact: Boolean,
    title: String,
    colors: HomeColors
) {
    if (bullets.isEmpty()) return

    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium.copy(
                color = colors.textMuted,
                fontWeight = FontWeight.SemiBold
            )
        )

        bullets.take(if (compact) 2 else 3).forEach { bullet ->
            BulletText(bullet, colors)
        }
    }
}


@Composable
private fun ExperienceInternItem(
    title: String,
    date: String,
    section: ExperienceSection,
    spec: ExperienceSpec,
    showTimeline: Boolean,
    isLast: Boolean,
    colors: HomeColors
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        if (showTimeline) TimelineRail(isLast = isLast, colors = colors)

        Column(
            modifier = Modifier
                .widthIn(max = spec.maxTextWidth)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RoleHeader(title = title, date = date, subtitle = section.highlight, colors = colors)
        }
    }
}


@Composable
private fun FocusAreasGrid(
    sections: List<ExperienceSection>,
    compact: Boolean,
    label: String,
    colors: HomeColors
) {
    val cols = if (compact) 1 else 2

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium.copy(
                color = colors.textMuted,
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
                            colors = colors,
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
