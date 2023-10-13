package com.s4a4.core.model.ktor

data class AnimeDetails(
    val voiceModels: List<VoiceModel>,
    val nameModels: List<NameModel>,
    val seriesModels: List<List<SeriesModel>>
)
