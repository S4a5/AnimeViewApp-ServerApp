package com.example.core.model.ktor

data class NameModel(
    val id: Int? = null,
    val voice_id: Int = -1,
    val ru: String?,
    val en: String?,
    val alternative: String?,
    val season: String?,
    val part: String?,
)
