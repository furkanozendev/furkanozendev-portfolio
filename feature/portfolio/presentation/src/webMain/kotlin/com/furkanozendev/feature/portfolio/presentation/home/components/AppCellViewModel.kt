package com.furkanozendev.feature.portfolio.presentation.home.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkanozendev.feature.portfolio.domain.usecase.ObserveStringResourcesUseCase
import furkanozendev.feature.portfolio.presentation.generated.resources.Res
import furkanozendev.feature.portfolio.presentation.generated.resources.cv_ic
import furkanozendev.feature.portfolio.presentation.generated.resources.github_ic
import furkanozendev.feature.portfolio.presentation.generated.resources.gmail_ic
import furkanozendev.feature.portfolio.presentation.generated.resources.linkedin_ic
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.jetbrains.compose.resources.DrawableResource

data class AppCellItemUiState(
    val title: String,
    val icon: DrawableResource,
    val url: String
)

data class AppCellUiState(
    val apps: List<AppCellItemUiState> = emptyList()
)

class AppCellViewModel(
    observeStrings: ObserveStringResourcesUseCase
) : ViewModel() {

    val uiState: StateFlow<AppCellUiState> = observeStrings()
        .map { res ->
            AppCellUiState(
                apps = listOf(
                    AppCellItemUiState(res["linkedin_title"] ?: "", Res.drawable.linkedin_ic, res["linkedin_url"] ?: ""),
                    AppCellItemUiState(res["github_title"] ?: "", Res.drawable.github_ic, res["github_url"] ?: ""),
                    AppCellItemUiState(res["email_title"] ?: "", Res.drawable.gmail_ic, res["email_url"] ?: ""),
                    AppCellItemUiState(res["download_cv_title"] ?: "", Res.drawable.cv_ic, res["download_cv_url"] ?: "")
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AppCellUiState()
        )
}
