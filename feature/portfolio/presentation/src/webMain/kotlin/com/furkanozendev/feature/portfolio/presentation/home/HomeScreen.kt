package com.furkanozendev.feature.portfolio.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.furkanozendev.core.designsystem.components.ContentBalancedRow
import com.furkanozendev.core.designsystem.util.glassGradientBackground
import com.furkanozendev.feature.portfolio.presentation.home.components.AppCell
import com.furkanozendev.feature.portfolio.presentation.home.components.LanguageToggleButton
import com.furkanozendev.feature.portfolio.presentation.home.infra.LocalStringResources
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
fun HomeScreen() {
    val viewModel: HomeViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val resourceManager by viewModel.stringResourceManager.collectAsState()

    CompositionLocalProvider(LocalStringResources provides resourceManager) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .glassGradientBackground()
        ) {
            val spec = rememberHomeLayoutSpec(maxWidth)
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .glassGradientBackground(),
                verticalArrangement = Arrangement.spacedBy(spec.gap)
            ) {
                Spacer(Modifier.height(24.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    LanguageToggleButton(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(horizontal = spec.gutter),
                        initialLanguage = uiState.selectedLanguage,
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .widthIn(max = spec.maxContentWidth)
                            .padding(horizontal = spec.gutter),
                        verticalArrangement = Arrangement.spacedBy(32.dp),
                    ) {
                        ProfileWidget(modifier = Modifier.fillMaxWidth())

                        AppCell(modifier = Modifier.fillMaxWidth())

                        if (spec.columns == 2) {
                            ContentBalancedRow(
                                modifier = Modifier.fillMaxWidth(),
                                gap = spec.gap,
                                content1 = { ExperienceWidget(modifier = Modifier.fillMaxSize()) },
                                content2 = { TechStackWidget(modifier = Modifier.fillMaxSize()) }
                            )
                        } else {
                            ExperienceWidget(modifier = Modifier.fillMaxWidth())
                            TechStackWidget(modifier = Modifier.fillMaxWidth())
                        }

                        ProjectsWidget(modifier = Modifier.fillMaxWidth())

                        WhatImReadingWidget(modifier = Modifier.fillMaxWidth())
                    }
                }

                Spacer(Modifier.height(24.dp))

                PortfolioFooter(modifier = Modifier.fillMaxWidth())

                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

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
