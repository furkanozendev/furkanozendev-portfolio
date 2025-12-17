package com.furkanozendev.feature.portfolio.presentation.home.widget.techstack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkanozendev.feature.portfolio.domain.usecase.ObserveStringResourcesUseCase
import com.furkanozendev.feature.portfolio.presentation.home.components.tech.Emphasis
import com.furkanozendev.feature.portfolio.presentation.home.components.tech.TechCategoryUi
import com.furkanozendev.feature.portfolio.presentation.home.infra.MapStringResourceManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TechStackViewModel(
    observeStrings: ObserveStringResourcesUseCase
) : ViewModel() {

    val uiState: StateFlow<TechStackUiState> = observeStrings()
        .map { stringMap ->
            val res = MapStringResourceManager(stringMap)
            TechStackUiState(
                title = res["tech_stack_title"],
                headerTitle = res["tech_stack_header_title"],
                headerSubtitle = res["tech_stack_header_subtitle"],
                primaryLabel = res["tech_stack_primary"],
                secondaryLabel = res["tech_stack_secondary"],
                categories = listOf(
                    TechCategoryUi(
                        title = res["tech_stack_android"],
                        subtitle = res["tech_stack_android_subtitle"],
                        emphasis = Emphasis.Primary,
                        items = res.getList("tech_stack_android_items")
                    ),
                    TechCategoryUi(
                        title = res["tech_stack_multiplatform"],
                        subtitle = res["tech_stack_multiplatform_subtitle"],
                        emphasis = Emphasis.Secondary,
                        items = res.getList("tech_stack_multiplatform_items")
                    ),
                    TechCategoryUi(
                        title = res["tech_stack_tooling"],
                        subtitle = res["tech_stack_tooling_subtitle"],
                        emphasis = Emphasis.Secondary,
                        items = res.getList("tech_stack_tooling_items")
                    )
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TechStackUiState()
        )
}
