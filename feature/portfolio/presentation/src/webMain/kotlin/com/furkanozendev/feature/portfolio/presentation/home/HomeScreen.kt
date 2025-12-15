package com.furkanozendev.feature.portfolio.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.furkanozendev.core.designsystem.colors.HomeColors.BaseBackground
import com.furkanozendev.core.designsystem.colors.HomeColors.BlueGlow
import com.furkanozendev.core.designsystem.colors.HomeColors.OrangeGlow
import com.furkanozendev.core.designsystem.components.SystemBottomBar
import com.furkanozendev.core.designsystem.components.SystemTopBar
import com.furkanozendev.feature.portfolio.presentation.home.components.AppCell
import com.furkanozendev.feature.portfolio.presentation.home.widget.ExperienceWidget
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
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SystemTopBar(
                modifier = Modifier.fillMaxWidth(),
                onExpandShade = { /* Optional: Click triggers full open */ }
            )

            HomeBody(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )

            SystemBottomBar(
                modifier = Modifier.fillMaxWidth(),
                onBackClicked = {},
                onHomeClicked = {}
            )
        }
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(spec.gap),
                        verticalAlignment = Alignment.Top
                    ) {
                        ExperienceWidget(modifier = Modifier.weight(0.52f))
                        TechStackWidget(modifier = Modifier.weight(0.48f))
                    }
                } else {
                    ExperienceWidget(modifier = Modifier.fillMaxWidth())
                    TechStackWidget(modifier = Modifier.fillMaxWidth())
                }

                ProjectsWidget(modifier = Modifier.fillMaxWidth())

                WhatImReadingWidget(modifier = Modifier.fillMaxWidth())
            }

            Spacer(Modifier.height(24.dp))
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
