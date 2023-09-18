package com.example.data

import com.example.core.model.anime_vost.AnimeVostModel
import com.example.core.model.anime_vost.series.SeriesAnimeVost
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AnimeVostService {
    @GET("last")
    suspend fun getPageAnime(
        @Query("page") page: Int,
        @Query("quantity") quantity: Int = 5
    ): Response<AnimeVostModel>

    @POST("/playlist")
    suspend fun getSeriesByAnime(@Field("id") idAnime: Int): Response<SeriesAnimeVost>

    @GET("genres")
    suspend fun getGenres():Response<Map<String,String>>
}