package com.example.core.model.anilibria.getChanges

data class SeriesAnimeData(
    val created_timestamp: Int,
    val episode: Int,
    val hls: Hls,
    val name: String,
    val preview: String,
    val skips: Skips,
    val uuid: String
)