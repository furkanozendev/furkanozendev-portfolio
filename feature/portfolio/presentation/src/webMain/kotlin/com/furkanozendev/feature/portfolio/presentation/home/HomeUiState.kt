package com.furkanozendev.feature.portfolio.presentation.home

import com.furkanozendev.feature.portfolio.domain.model.AppLanguage

internal data class HomeUiState(
    val selectedLanguage: AppLanguage = AppLanguage.EN
)