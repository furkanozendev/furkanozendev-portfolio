package com.furkanozendev.feature.portfolio.domain.infra

interface StringResourceManager {
    operator fun get(id: String): String
    operator fun get(id: String, args: List<String>): String

    fun getList(id: String): List<String>
}