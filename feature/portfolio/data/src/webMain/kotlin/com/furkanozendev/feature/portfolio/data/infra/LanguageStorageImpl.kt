package com.furkanozendev.feature.portfolio.data.infra

import com.furkanozendev.feature.portfolio.domain.infra.LanguageStorage
import kotlinx.browser.window

class LanguageStorageImpl : LanguageStorage {
    private val key = "user_selected_language"

    override fun getSavedLanguage(): String {
        val saved = window.localStorage.getItem(key)
        if (saved != null) return saved

        val browserLang = window.navigator.language.take(2)
        return browserLang
    }

    override fun saveLanguage(languageCode: String) {
        window.localStorage.setItem(key, languageCode)
    }
}