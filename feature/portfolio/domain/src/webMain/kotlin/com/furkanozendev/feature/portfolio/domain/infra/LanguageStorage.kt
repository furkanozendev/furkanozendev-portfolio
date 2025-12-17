package com.furkanozendev.feature.portfolio.domain.infra

interface LanguageStorage {
    fun getSavedLanguage(): String
    fun saveLanguage(languageCode: String)
}