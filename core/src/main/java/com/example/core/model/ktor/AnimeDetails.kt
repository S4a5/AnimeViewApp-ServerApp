package com.example.core.model.ktor

data class AnimeDetails(
    val voiceModels: List<VoiceModel>,
    val nameModels: List<NameModel>,
    val seriesModels: List<List<SeriesModel>>
)
