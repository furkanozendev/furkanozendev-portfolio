package com.furkanozendev.feature.portfolio.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.furkanozendev.core.designsystem.colors.HomeColors
import com.furkanozendev.core.designsystem.components.SystemBottomBar
import com.furkanozendev.core.designsystem.components.SystemTopBar
import com.furkanozendev.feature.portfolio.presentation.home.components.HomeBody
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HomeColors.BodyBackground)
    ) {
        SystemTopBar(
            modifier = Modifier.fillMaxWidth(),
            onExpandShade = {},
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


