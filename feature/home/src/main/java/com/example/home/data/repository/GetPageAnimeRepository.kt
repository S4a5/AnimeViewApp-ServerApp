package com.example.home.data.repository

import com.example.core.model.ktor.AnimeDetails
import retrofit2.Response

interface GetPageAnimeRepository {
    suspend fun getPage(page:Int,count:Int): Response<List<AnimeDetails>>
}