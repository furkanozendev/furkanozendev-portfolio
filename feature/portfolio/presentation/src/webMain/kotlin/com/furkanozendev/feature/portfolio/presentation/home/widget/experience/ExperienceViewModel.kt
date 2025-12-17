package com.furkanozendev.feature.portfolio.presentation.home.widget.experience

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkanozendev.feature.portfolio.domain.usecase.ObserveStringResourcesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ExperienceViewModel(
    observeStrings: ObserveStringResourcesUseCase
) : ViewModel() {

    val uiState: StateFlow<ExperienceUiState> = observeStrings()
        .map { res ->
            val androidDevSections = listOf(
                ExperienceSectionUiState(
                    heading = res["experience_section_product"] ?: "",
                    highlight = res["experience_section_product_highlight"]
                ),
                ExperienceSectionUiState(
                    heading = res["experience_section_accessibility"] ?: "",
                    highlight = res["experience_section_accessibility_highlight"]
                ),
                ExperienceSectionUiState(
                    heading = res["experience_section_architecture"] ?: "",
                    highlight = res["experience_section_architecture_highlight"]
                ),
                ExperienceSectionUiState(
                    heading = res["experience_section_testing"] ?: "",
                    highlight = res["experience_section_testing_highlight"]
                )
            )

            val androidDevKeyWins = listOf(
                res["experience_key_wins_1"] ?: "",
                res["experience_key_wins_2"] ?: "",
                res["experience_key_wins_3"] ?: ""
            )

            val internSection = ExperienceSectionUiState(
                heading = res["experience_section_internship"] ?: "",
                highlight = res["experience_section_internship_highlight"]
            )

            ExperienceUiState(
                title = res["experience_title"] ?: "",
                headerTitle = res["experience_header_title"] ?: "",
                headerSubtitle = res["experience_header_subtitle"] ?: "",
                company = res["experience_company_papara"] ?: "",
                companyPeriod = res["experience_papara_period"] ?: "",
                roles = listOf(
                    ExperienceRoleUiState(
                        title = res["experience_role_android_dev"] ?: "",
                        date = res["experience_role_android_dev_period"] ?: "",
                        subtitle = res["experience_role_android_dev_subtitle"],
                        sections = androidDevSections,
                        keyWins = androidDevKeyWins,
                        keyWinsTitle = res["experience_key_wins_title"] ?: "",
                        focusAreasLabel = res["experience_focus_areas"] ?: "",
                        isIntern = false
                    ),
                    ExperienceRoleUiState(
                        title = res["experience_role_intern"] ?: "",
                        date = res["experience_role_intern_period"] ?: "",
                        sections = listOf(internSection),
                        keyWins = emptyList(),
                        keyWinsTitle = "",
                        focusAreasLabel = "",
                        isIntern = true
                    )
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ExperienceUiState()
        )
}
