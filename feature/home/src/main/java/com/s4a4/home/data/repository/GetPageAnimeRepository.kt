package com.s4a4.home.data.repository

import com.s4a4.core.model.ktor.AnimeDetails
import retrofit2.Response

interface GetPageAnimeRepository {
    suspend fun getPage(page:Int,count:Int): Response<List<AnimeDetails>>
}