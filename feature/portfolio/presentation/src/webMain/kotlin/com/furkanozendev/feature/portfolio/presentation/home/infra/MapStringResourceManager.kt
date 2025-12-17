package com.furkanozendev.feature.portfolio.presentation.home.infra

import com.furkanozendev.feature.portfolio.domain.infra.StringResourceManager

class MapStringResourceManager(
    private val stringMap: Map<String, String>
) : StringResourceManager {

    override operator fun get(id: String): String {
        return stringMap[id] ?: ""
    }

    override operator fun get(id: String, args: List<String>): String {
        var template = stringMap[id] ?: return ""

        args.forEachIndexed { index, arg ->
            template = template.replace("{$index}", arg)
        }
        return template
    }

    override fun getList(id: String): List<String> {
        val rawValue = stringMap[id] ?: return emptyList()

        if (rawValue.isBlank()) return emptyList()

        return rawValue.split(",").map { it.trim() }
    }
}