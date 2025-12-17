package com.furkanozendev.feature.portfolio.domain.repository

import kotlinx.coroutines.flow.Flow

interface StringRepository {
    val stringsFlow: Flow<Map<String, String>>

    suspend fun fetchStrings(url: String)
}