package com.example.data

import com.example.core.model.anilibria.getChanges.AnilibriaModel
import com.example.core.model.anilibria.getChanges.AnimeTitle
import retrofit2.Response
import retrofit2.http.GET

interface AnilibriaService {

    @GET("v3/title/changes")
    suspend fun getChanges():Response<AnilibriaModel>
}