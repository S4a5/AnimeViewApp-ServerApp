package com.example.home.data.anime_vost.repository.anilibria

import com.example.core.model.anilibria.getChanges.AnilibriaModel
import com.example.core.model.anilibria.getChanges.AnimeTitle
import retrofit2.Response

interface PageAnilibriaRepository {
    suspend fun getPageAnime(): Response<AnilibriaModel>


}