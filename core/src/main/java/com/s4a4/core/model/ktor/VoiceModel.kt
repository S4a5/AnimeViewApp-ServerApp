package com.s4a4.core.model.ktor

data class VoiceModel(
    val id: Int? = null,
    val anime_id: Int = -1,
    val titile_id: Int,
    val voiceGrupe: String,
    val description: String?,
    val genre: String?,
    var urlImagePreview: String?,
    val screenImage: String?,
    val seriesCount: Int?,
    val last_change: Long?,
    val updated: Int?,
) {
    fun selectAnime(animeId: Int) {

    }
}
