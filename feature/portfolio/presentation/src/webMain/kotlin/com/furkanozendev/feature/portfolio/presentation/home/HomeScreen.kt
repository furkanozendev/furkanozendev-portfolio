package com.furkanozendev.feature.portfolio.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.furkanozendev.core.designsystem.colors.HomeColors
import com.furkanozendev.core.designsystem.components.SystemBottomBar
import com.furkanozendev.core.designsystem.components.SystemTopBar
import com.furkanozendev.feature.portfolio.presentation.home.components.AppCell
import com.furkanozendev.feature.portfolio.presentation.home.components.NotificationShade
import com.furkanozendev.feature.portfolio.presentation.home.state.ShadeState
import com.furkanozendev.feature.portfolio.presentation.home.state.mouseWheelShadeTrigger
import com.furkanozendev.feature.portfolio.presentation.home.state.rememberShadeState
import com.furkanozendev.feature.portfolio.presentation.home.state.shadeTrigger
import com.furkanozendev.feature.portfolio.presentation.home.state.smartMouseWheelTrigger
import com.furkanozendev.feature.portfolio.presentation.home.widget.ExperienceWidget
import com.furkanozendev.feature.portfolio.presentation.home.widget.ProfileWidget
import com.furkanozendev.feature.portfolio.presentation.home.widget.ProjectsWidget
import com.furkanozendev.feature.portfolio.presentation.home.widget.TechStackWidget
import com.furkanozendev.feature.portfolio.presentation.home.widget.WhatImReadingWidget
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val shadeState = rememberShadeState()

    val blurRadius = (40.dp * shadeState.progress)
    val dimAlpha = (0.5f * shadeState.progress)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HomeColors.BodyBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(shadeState.nestedScrollConnection)
                .blur(radius = blurRadius)
                .drawWithContent {
                    drawContent()
                    drawRect(Color.Black.copy(alpha = dimAlpha))
                }
        ) {
            SystemTopBar(
                modifier = Modifier.fillMaxWidth(),
                onExpandShade = { /* Optional: Click triggers full open */ }
            )

            HomeBody(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                shadeState = shadeState
            )

            SystemBottomBar(
                modifier = Modifier.fillMaxWidth(),
                onBackClicked = {},
                onHomeClicked = {}
            )
        }

        NotificationShade(
            progress = shadeState.progress,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}

@Composable
private fun HomeBody(
    modifier: Modifier = Modifier,
    shadeState: ShadeState
) {
    BoxWithConstraints(modifier = modifier) {
        val isWideLayout = maxWidth > 800.dp

        if (isWideLayout) {
            Column(
                modifier = Modifier
                    .shadeTrigger(shadeState)
                    .mouseWheelShadeTrigger(shadeState)
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(52.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier
                            .weight(0.4f)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        ProfileWidget(modifier = Modifier.fillMaxWidth())
                        AppCell(modifier = Modifier.fillMaxWidth())
                        TechStackWidget(modifier = Modifier.fillMaxWidth())
                    }

                    Column(
                        modifier = Modifier
                            .weight(0.6f)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        ExperienceWidget(modifier = Modifier.fillMaxWidth())

                        Row(modifier = Modifier.fillMaxWidth()) {
                            ProjectsWidget(modifier = Modifier.fillMaxHeight().weight(1f))
                            Spacer(modifier = Modifier.width(16.dp))
                            WhatImReadingWidget(modifier = Modifier.fillMaxHeight().weight(1f))
                        }
                    }
                }
            }
        } else {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .smartMouseWheelTrigger(shadeState, scrollState)
                    .verticalScroll(scrollState)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProfileWidget(modifier = Modifier.fillMaxWidth())
                AppCell(modifier = Modifier.fillMaxWidth())
                ExperienceWidget(modifier = Modifier.fillMaxWidth())
                TechStackWidget(modifier = Modifier.fillMaxWidth())
                ProjectsWidget(modifier = Modifier.fillMaxWidth())
                WhatImReadingWidget(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

