package com.example.home.data.anime_vost.repository

import com.example.core.model.anime_vost.AnimeVostModel

interface PageAnimeRepository {
    suspend fun getPageAnime(
        page: Int,
        quantity: Int = 10
    ): AnimeVostModel?
}