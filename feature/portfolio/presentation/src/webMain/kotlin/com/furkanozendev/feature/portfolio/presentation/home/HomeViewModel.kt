package com.furkanozendev.feature.portfolio.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkanozendev.feature.portfolio.domain.infra.StringResourceManager
import com.furkanozendev.feature.portfolio.domain.model.AppLanguage
import com.furkanozendev.feature.portfolio.domain.usecase.ObserveStringResourcesUseCase
import com.furkanozendev.feature.portfolio.domain.usecase.SyncStringResourcesUseCase
import com.furkanozendev.feature.portfolio.presentation.home.infra.MapStringResourceManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val observeStrings: ObserveStringResourcesUseCase,
    private val syncStrings: SyncStringResourcesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    val stringResourceManager: StateFlow<StringResourceManager> = observeStrings()
        .map { stringMap ->
            MapStringResourceManager(stringMap)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MapStringResourceManager(emptyMap())
        )

    init {
        loadStrings()
    }

    private fun loadStrings() {
        viewModelScope.launch {
            syncStrings("en")
        }
    }

    fun updateLanguage(appLanguage: AppLanguage) {
        _uiState.value = _uiState.value.copy(selectedLanguage = appLanguage)
    }
}