package com.furkanozendev.feature.portfolio.data.repository

import com.furkanozendev.feature.portfolio.domain.repository.StringRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class StringRepositoryImpl(private val client: HttpClient) : StringRepository {

    // Internal mutable state
    private val _stringsFlow = MutableStateFlow<Map<String, String>>(emptyMap())

    // Public read-only flow
    override val stringsFlow: Flow<Map<String, String>> = _stringsFlow

    override suspend fun fetchStrings(code: String) {
        try {
            val url = "https://raw.githubusercontent.com/furkanozendev/portfolio/main/data/$code.json"
            val map = client.get(url).body<Map<String, String>>()
            _stringsFlow.value = map
        } catch (e: Exception) {
            println("Error fetching strings: ${e.message}")
            // Keep existing data or load local fallback
        }
    }
}