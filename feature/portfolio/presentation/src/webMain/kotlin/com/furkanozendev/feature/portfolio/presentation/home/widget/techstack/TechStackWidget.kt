package com.furkanozendev.feature.portfolio.presentation.home.widget.techstack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.furkanozendev.core.designsystem.colors.LocalHomeColors
import com.furkanozendev.core.designsystem.components.SectionHeader
import com.furkanozendev.feature.portfolio.presentation.home.components.BentoCard
import com.furkanozendev.feature.portfolio.presentation.home.components.tech.Emphasis
import com.furkanozendev.feature.portfolio.presentation.home.components.tech.TechCategoryCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TechStackWidget(
    modifier: Modifier = Modifier,
    viewModel: TechStackViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val colors = LocalHomeColors.current

    val techCategories = uiState.categories

    BentoCard(
        modifier = modifier,
        title = uiState.title,
        icon = Icons.Rounded.Code
    ) {
        BoxWithConstraints(Modifier) {
            val isCompact = maxWidth < 520.dp
            val isWide = maxWidth >= 900.dp

            val padding = when {
                isWide -> 28.dp
                isCompact -> 18.dp
                else -> 22.dp
            }
            val gap = if (isCompact) 14.dp else 16.dp

            val (primary, secondary) = techCategories.partition { it.emphasis == Emphasis.Primary }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(gap)
            ) {
                SectionHeader(
                    title = uiState.headerTitle,
                    subtitle = uiState.headerSubtitle,
                    textPrimary = colors.textPrimary,
                    textMuted = colors.textMuted
                )

                Text(
                    text = uiState.primaryLabel,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = colors.textMuted,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                TechCategoryCard(
                    category = primary.firstOrNull(),
                    accent = colors.accentPrimary,
                    textPrimary = colors.textPrimary,
                    textMuted = colors.textMuted,
                    colors = colors
                )

                Text(
                    text = uiState.secondaryLabel,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = colors.textMuted,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(gap)
                ) {
                    secondary.forEach { cat ->
                        TechCategoryCard(
                            category = cat,
                            accent = colors.accentSecondary,
                            textPrimary = colors.textPrimary,
                            textMuted = colors.textMuted,
                            colors = colors
                        )
                    }
                }
            }
        }
    }
}