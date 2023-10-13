package com.s4a4.core.model.ktor

data class SeriesModel(
    val id: Int,
    val voice_id: Int = -1,
    val name: String?,
    val preview: String?,
    val fhd: String?,
    val hd: String?,
    val std: String?,
)
