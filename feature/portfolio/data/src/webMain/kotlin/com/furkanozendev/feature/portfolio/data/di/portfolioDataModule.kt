package com.furkanozendev.feature.portfolio.data.di

import com.furkanozendev.feature.portfolio.data.infra.LanguageStorageImpl
import com.furkanozendev.feature.portfolio.data.repository.StringRepositoryImpl
import com.furkanozendev.feature.portfolio.domain.infra.LanguageStorage
import com.furkanozendev.feature.portfolio.domain.repository.StringRepository
import com.furkanozendev.feature.portfolio.domain.usecase.ObserveStringResourcesUseCase
import com.furkanozendev.feature.portfolio.domain.usecase.SyncStringResourcesUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val portfolioDataModule = module {
    single<LanguageStorage> { LanguageStorageImpl() }

    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                val json = Json {
                    ignoreUnknownKeys = true
                }
                json(json, contentType = ContentType.Application.Json)
                json(json, contentType = ContentType.Text.Plain)
            }
        }
    }

    single<StringRepository> { StringRepositoryImpl(get()) }

    factory { ObserveStringResourcesUseCase(get()) }
    factory { SyncStringResourcesUseCase(get()) }
}