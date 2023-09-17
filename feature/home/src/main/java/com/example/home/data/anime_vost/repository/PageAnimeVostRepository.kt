package com.example.home.data.anime_vost.repository

import com.example.core.model.anime_vost.AnimeVostModel
import retrofit2.Response

interface PageAnimeVostRepository {
    suspend fun getPageAnime(
        page: Int,
        quantity: Int = 30
    ): Response<AnimeVostModel>
}