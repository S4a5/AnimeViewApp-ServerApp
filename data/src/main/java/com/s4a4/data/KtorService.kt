package com.s4a4.data

import com.s4a4.core.model.ktor.AnimeDetails
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface KtorService {
    @POST("list")
    suspend fun getPageAnime(
        @Query("page") page: Int,
        @Query("count") quantity: Int = 5
    ): Response<List<AnimeDetails>>

    @GET("search")
    suspend fun getAnimeByName(
        @Query("search") search: String,
    ): Response<List<AnimeDetails>>

    @GET("genres")
    suspend fun getAllGenres(): Response<List<String>>

    @POST("/search/by/genres")
    suspend fun getAnimeByGenre(@Body list: List<String>): Response<List<AnimeDetails>>

    @POST("list/update")
    suspend fun getNewLastList(
        @Query("page") page: Int,
        @Query("count") quantity: Int = 5
    ): Response<List<AnimeDetails>>
}