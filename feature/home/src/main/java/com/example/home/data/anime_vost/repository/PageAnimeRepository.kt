package com.example.home.data.anime_vost.repository

import com.example.core.model.AnimeDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PageAnimeRepository {


    suspend fun getPageAnime(
        page: Int, quantity: Int,
    ):  Response<List<AnimeDetails>>
}