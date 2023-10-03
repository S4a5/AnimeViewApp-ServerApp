package com.example.data

import com.example.core.model.anime_vost.AnimeVostModel
import com.example.core.model.ktor.AnimeDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface KtorService {
    @POST("list")
    suspend fun getPageAnime(
        @Query("page") page: Int,
        @Query("count") quantity: Int = 5
    ): Response<List<AnimeDetails>>

}