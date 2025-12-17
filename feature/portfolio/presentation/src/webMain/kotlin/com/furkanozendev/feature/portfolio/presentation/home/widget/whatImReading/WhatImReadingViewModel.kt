package com.furkanozendev.feature.portfolio.presentation.home.widget.whatImReading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkanozendev.feature.portfolio.domain.usecase.ObserveStringResourcesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class WhatImReadingViewModel(
    observeStrings: ObserveStringResourcesUseCase
) : ViewModel() {

    val uiState: StateFlow<WhatImReadingUiState> = observeStrings()
        .map { res ->
            WhatImReadingUiState(
                title = res["reading_title"] ?: "",
                headerSubtitle = res["reading_header_subtitle"] ?: "",
                emptyTitle = res["reading_empty_title"] ?: "",
                emptySubtitle = res["reading_empty_subtitle"] ?: "",
                items = listOf(
                    // TODO Add Later
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WhatImReadingUiState()
        )
}
