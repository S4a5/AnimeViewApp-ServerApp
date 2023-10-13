package com.s4a4.data

import com.s4a4.core.model.anilibria.getChanges.AnilibriaModel
import com.s4a4.core.model.anilibria.getChanges.AnimeTitle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AnilibriaService {

    @GET("v3/title/changes")
    suspend fun getChanges(
        @Query("page") page: Int,
    ):Response<AnilibriaModel>
}