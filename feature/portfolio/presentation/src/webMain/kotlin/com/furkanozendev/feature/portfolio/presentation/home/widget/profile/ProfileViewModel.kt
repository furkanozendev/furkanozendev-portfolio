package com.furkanozendev.feature.portfolio.presentation.home.widget.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkanozendev.feature.portfolio.domain.usecase.ObserveStringResourcesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ProfileViewModel(
    observeStrings: ObserveStringResourcesUseCase
) : ViewModel() {

    val uiState: StateFlow<ProfileUiState> = observeStrings()
        .map { strings ->
            ProfileUiState(
                name = strings["name"] ?: "",
                title = strings["title"] ?: "",
                tagline = strings["tagline"] ?: ""
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ProfileUiState()
        )
}
