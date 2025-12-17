package com.furkanozendev.feature.portfolio.presentation.home.di

import com.furkanozendev.feature.portfolio.presentation.home.HomeViewModel
import com.furkanozendev.feature.portfolio.presentation.home.components.AppCellViewModel
import com.furkanozendev.feature.portfolio.presentation.home.widget.experience.ExperienceViewModel
import com.furkanozendev.feature.portfolio.presentation.home.widget.footer.PortfolioFooterViewModel
import com.furkanozendev.feature.portfolio.presentation.home.widget.profile.ProfileViewModel
import com.furkanozendev.feature.portfolio.presentation.home.widget.projects.ProjectsViewModel
import com.furkanozendev.feature.portfolio.presentation.home.widget.techstack.TechStackViewModel
import com.furkanozendev.feature.portfolio.presentation.home.widget.whatImReading.WhatImReadingViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val portfolioPresentationModule = module {
    factoryOf(::HomeViewModel)
    factoryOf(::ProfileViewModel)
    factoryOf(::ProjectsViewModel)
    factoryOf(::TechStackViewModel)
    factoryOf(::ExperienceViewModel)
    factoryOf(::WhatImReadingViewModel)
    factoryOf(::PortfolioFooterViewModel)
    factoryOf(::AppCellViewModel)
}