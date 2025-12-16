package com.furkanozendev.feature.portfolio.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.furkanozendev.core.designsystem.colors.HomeColors.BaseBackground
import com.furkanozendev.core.designsystem.colors.HomeColors.BlueGlow
import com.furkanozendev.core.designsystem.colors.HomeColors.OrangeGlow
import com.furkanozendev.feature.portfolio.presentation.home.components.AppCell
import com.furkanozendev.feature.portfolio.presentation.home.components.LanguageToggleButton
import com.furkanozendev.feature.portfolio.presentation.home.widget.ExperienceWidget
import com.furkanozendev.feature.portfolio.presentation.home.widget.PortfolioFooter
import com.furkanozendev.feature.portfolio.presentation.home.widget.ProfileWidget
import com.furkanozendev.feature.portfolio.presentation.home.widget.ProjectsWidget
import com.furkanozendev.feature.portfolio.presentation.home.widget.TechStackWidget
import com.furkanozendev.feature.portfolio.presentation.home.widget.WhatImReadingWidget
import org.koin.compose.viewmodel.koinViewModel

@Immutable
private data class HomeLayoutSpec(
    val maxContentWidth: Dp,
    val gutter: Dp,
    val gap: Dp,
    val columns: Int
)

@Composable
private fun rememberHomeLayoutSpec(maxWidth: Dp): HomeLayoutSpec {
    val compact = maxWidth < 600.dp
    val wide = maxWidth >= 980.dp

    return remember(maxWidth) {
        HomeLayoutSpec(
            maxContentWidth = 1180.dp,
            gutter = if (compact) 16.dp else 52.dp,
            gap = if (compact) 14.dp else 16.dp,
            columns = if (wide) 2 else 1
        )
    }
}

@Composable
private fun CenteredContent(
    modifier: Modifier = Modifier,
    maxWidth: Dp,
    gutter: Dp,
    arrangementGap: Dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        LanguageToggleButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(horizontal = gutter)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = maxWidth)
                .padding(horizontal = gutter),
            content = content,
            verticalArrangement = Arrangement.spacedBy(arrangementGap),
        )
    }
}

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .glassGradientBackground()
    ) {
        HomeBody(modifier = Modifier.fillMaxSize())
    }
}

@Composable
private fun HomeBody(
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier) {
        val spec = rememberHomeLayoutSpec(maxWidth)
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(spec.gap)
        ) {
            Spacer(Modifier.height(24.dp))

            CenteredContent(
                maxWidth = spec.maxContentWidth,
                gutter = spec.gutter,
                arrangementGap = 32.dp
            ) {
                ProfileWidget(modifier = Modifier.fillMaxWidth())

                AppCell(modifier = Modifier.fillMaxWidth())

                if (spec.columns == 2) {
                    ContentBalancedRow(
                        modifier = Modifier.fillMaxWidth(),
                        gap = spec.gap,
                        content1 = {
                            ExperienceWidget(modifier = Modifier.fillMaxSize())
                        },
                        content2 = {
                            TechStackWidget(modifier = Modifier.fillMaxSize())
                        }
                    )
                } else {
                    ExperienceWidget(modifier = Modifier.fillMaxWidth())
                    TechStackWidget(modifier = Modifier.fillMaxWidth())
                }

                ProjectsWidget(modifier = Modifier.fillMaxWidth())

                WhatImReadingWidget(modifier = Modifier.fillMaxWidth())
            }

            Spacer(Modifier.height(24.dp))

            PortfolioFooter(modifier = Modifier.fillMaxWidth())

            Spacer(Modifier.height(8.dp))
        }
    }
}


fun Modifier.glassGradientBackground(): Modifier = this.then(
    Modifier.drawBehind {
        drawRect(color = BaseBackground)

        drawRect(
            brush = Brush.radialGradient(
                colors = listOf(BlueGlow, Color.Transparent),
                center = Offset(0f, 0f),
                radius = size.maxDimension * 0.9f
            )
        )

        drawRect(
            brush = Brush.radialGradient(
                colors = listOf(OrangeGlow, Color.Transparent),
                center = Offset(size.width, size.height),
                radius = size.maxDimension * 0.9f
            )
        )
    }
)

@Composable
fun ContentBalancedRow(
    modifier: Modifier = Modifier,
    gap: Dp = 0.dp,
    content1: @Composable () -> Unit,
    content2: @Composable () -> Unit
) {
    SubcomposeLayout(modifier = modifier) { constraints ->
        val maxWidth = constraints.maxWidth
        if (maxWidth == Constraints.Infinity || maxWidth <= 0) {
            val p1 = subcompose("fallback1", content1).first().measure(Constraints())
            val p2 = subcompose("fallback2", content2).first().measure(Constraints())
            val h = maxOf(p1.height, p2.height).coerceAtLeast(1)
            return@SubcomposeLayout layout(p1.width + p2.width, h) {
                p1.place(0, 0)
                p2.place(p1.width, 0)
            }
        }

        val gapPx = gap.roundToPx().coerceAtLeast(0)
        val availableWidth = (maxWidth - gapPx).coerceAtLeast(0)

        val probeConstraints = Constraints(maxWidth = availableWidth / 2)
        val probe1 = subcompose("probe1", content1).first().measure(probeConstraints)
        val probe2 = subcompose("probe2", content2).first().measure(probeConstraints)

        val totalH = probe1.height + probe2.height
        val weight1 = if (totalH == 0) 0.5f else probe1.height.toFloat() / totalH
        val w1Ratio = weight1.coerceIn(0.35f, 0.65f)

        val w1 = (availableWidth * w1Ratio).toInt().coerceAtLeast(1)
        val w2 = (availableWidth - w1).coerceAtLeast(1)

        val natural1 = subcompose("natural1", content1).first()
            .measure(Constraints.fixedWidth(w1))
        val natural2 = subcompose("natural2", content2).first()
            .measure(Constraints.fixedWidth(w2))

        val maxH = maxOf(natural1.height, natural2.height).coerceAtLeast(1)

        val finalConstraints1 = Constraints.fixed(w1, maxH)
        val finalConstraints2 = Constraints.fixed(w2, maxH)

        val final1 = subcompose("final1") {
            Box(Modifier.fillMaxSize()) { content1() }
        }.first().measure(finalConstraints1)

        val final2 = subcompose("final2") {
            Box(Modifier.fillMaxSize()) { content2() }
        }.first().measure(finalConstraints2)

        layout(maxWidth, maxH) {
            final1.place(0, 0)
            final2.place(w1 + gapPx, 0)
        }
    }
}
