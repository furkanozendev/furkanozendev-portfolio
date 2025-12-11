package com.furkanozendev.feature.portfolio.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.furkanozendev.feature.portfolio.presentation.home.components.HomeBody
import com.furkanozendev.feature.portfolio.presentation.home.components.NotificationShade
import com.furkanozendev.feature.portfolio.presentation.home.state.rememberShadeState
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


