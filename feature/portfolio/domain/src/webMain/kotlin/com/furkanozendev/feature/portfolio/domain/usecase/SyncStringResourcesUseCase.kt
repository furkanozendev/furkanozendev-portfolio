package com.furkanozendev.feature.portfolio.domain.usecase

import com.furkanozendev.feature.portfolio.domain.repository.StringRepository

class SyncStringResourcesUseCase(
    private val repository: StringRepository
) {
    suspend operator fun invoke(languageCode: String) {
        repository.fetchStrings(languageCode)
    }
}