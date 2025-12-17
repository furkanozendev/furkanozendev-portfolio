package com.furkanozendev.feature.portfolio.domain.usecase

import com.furkanozendev.feature.portfolio.domain.repository.StringRepository
import kotlinx.coroutines.flow.Flow

class ObserveStringResourcesUseCase(
    private val repository: StringRepository
) {
    operator fun invoke(): Flow<Map<String, String>> {
        return repository.stringsFlow
    }
}