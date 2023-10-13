package com.s4a4.core.model.anime_vost

data class Data(
    val description: String,
    val director: String,
    val genre: String,
    val id: Int,
    val isFavorite: Int,
    val isLikes: Int,
    val rating: Int,
    val screenImage: List<String>,
    val series: String,
    val timer: Int,
    val title: String,
    val type: String,
    val urlImagePreview: String,
    val votes: Int,
    val year: String
)