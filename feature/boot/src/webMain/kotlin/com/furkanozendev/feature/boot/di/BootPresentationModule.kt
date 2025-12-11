package com.furkanozendev.feature.boot.di

import com.furkanozendev.feature.boot.BootViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val bootPresentationModule = module {
    factoryOf(::BootViewModel)
}