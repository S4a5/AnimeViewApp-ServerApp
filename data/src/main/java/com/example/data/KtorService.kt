package com.example.data

import com.example.core.model.AnimeDetails
import com.example.core.model.anilibria.getChanges.AnilibriaModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface KtorService {
    @POST("/list")
    suspend fun getPageAnime(
        @Query("page") page: Int,
        @Query("count") quantity: Int,
    ): Response<List<AnimeDetails>>
}
