package com.example.core.model

import com.example.core.model.ktor.NameModel
import com.example.core.model.ktor.SeriesModel
import com.example.core.model.ktor.VoiceModel

data class AnimeDetails(
    val voiceModels: List<VoiceModel>,
    val nameModels: List<NameModel>,
    val seriesModels: List<List<SeriesModel>>
)