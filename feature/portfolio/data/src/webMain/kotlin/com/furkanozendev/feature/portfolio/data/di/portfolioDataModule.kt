package com.furkanozendev.feature.portfolio.data.di

import com.furkanozendev.feature.portfolio.data.infra.LanguageStorageImpl
import com.furkanozendev.feature.portfolio.data.repository.StringRepositoryImpl
import com.furkanozendev.feature.portfolio.domain.infra.LanguageStorage
import com.furkanozendev.feature.portfolio.domain.repository.StringRepository
import com.furkanozendev.feature.portfolio.domain.usecase.ObserveStringResourcesUseCase
import com.furkanozendev.feature.portfolio.domain.usecase.SyncStringResourcesUseCase
import org.koin.dsl.module

val portfolioDataModule = module {
    single<LanguageStorage> { LanguageStorageImpl() }

    single<StringRepository> { StringRepositoryImpl(get()) }

    factory { ObserveStringResourcesUseCase(get()) }
    factory { SyncStringResourcesUseCase(get()) }
}