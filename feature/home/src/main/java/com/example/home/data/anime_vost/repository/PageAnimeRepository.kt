package com.example.home.data.anime_vost.repository

import com.example.core.model.ktor.AnimeDetails
import retrofit2.Response

interface PageAnimeRepository {
    suspend fun getPageAnime(
        page: Int, quantity: Int,
    ):  Response<List<AnimeDetails>>
}