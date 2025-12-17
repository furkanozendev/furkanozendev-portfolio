package com.furkanozendev.feature.portfolio.presentation.home.widget.footer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkanozendev.feature.portfolio.domain.usecase.ObserveStringResourcesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PortfolioFooterViewModel(
    observeStrings: ObserveStringResourcesUseCase
) : ViewModel() {

    val uiState: StateFlow<PortfolioFooterUiState> = observeStrings()
        .map { res ->
            PortfolioFooterUiState(
                tagline = res["footer_tagline"] ?: "",
                copyright = res["footer_copyright"] ?: "",
                githubTitle = res["github_title"] ?: "",
                githubUrl = res["github_url"] ?: "",
                linkedinTitle = res["linkedin_title"] ?: "",
                linkedinUrl = res["linkedin_url"] ?: ""
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PortfolioFooterUiState()
        )
}
