package com.example.core.model.ktor

data class VoiceModel(
    val id: Int? = null,
    val anime_id: Int = -1,
    val titile_id: Int,
    val voiceGrupe: String,
    val description: String?,
    val genre: String?,
    val urlImagePreview: String?,
    val screenImage: String?,
    val seriesCount: Int?,
    val last_change: Int?,
    val updated: Int?,
)
