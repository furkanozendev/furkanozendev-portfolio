package com.furkanozendev

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.furkanozendev.feature.boot.di.bootPresentationModule
import com.furkanozendev.feature.portfolio.presentation.home.HomeScreen
import com.furkanozendev.feature.portfolio.presentation.home.di.portfolioPresentationModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinMultiplatformApplication
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.koinConfiguration

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    KoinMultiplatformApplication(
        config = koinConfiguration {
            modules(bootPresentationModule)
            modules(portfolioPresentationModule)
        }
    ) {
        MaterialTheme {
            HomeScreen()
        }
    }
}
