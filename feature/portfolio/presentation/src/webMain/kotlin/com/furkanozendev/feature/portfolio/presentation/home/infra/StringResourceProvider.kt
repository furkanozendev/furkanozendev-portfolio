package com.furkanozendev.feature.portfolio.presentation.home.infra

import androidx.compose.runtime.staticCompositionLocalOf
import com.furkanozendev.feature.portfolio.domain.infra.StringResourceManager

val LocalStringResources = staticCompositionLocalOf<StringResourceManager> {
    error("No StringResourceManager provided")
}