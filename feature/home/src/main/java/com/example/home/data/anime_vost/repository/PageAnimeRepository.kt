package com.example.home.data.anime_vost.repository

import com.example.core.model.StateUi
import com.example.core.model.ktor.AnimeDetails
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response

interface PageAnimeRepository {

    val lastAnimeFlow:StateFlow<List<AnimeDetails>>
    suspend fun requestNewAnime()
}