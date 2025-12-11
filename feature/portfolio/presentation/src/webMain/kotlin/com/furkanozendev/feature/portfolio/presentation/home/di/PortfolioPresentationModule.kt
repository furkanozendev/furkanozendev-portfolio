package com.furkanozendev.feature.portfolio.presentation.home.di

import com.furkanozendev.feature.portfolio.presentation.home.HomeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val portfolioPresentationModule = module {
    factoryOf(::HomeViewModel)
}