package com.s4a4.home.data.anime_vost.repository

import com.s4a4.core.model.StateUi
import com.s4a4.core.model.ktor.AnimeDetails
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response

interface PageAnimeRepository {

    val lastAnimeFlow:StateFlow<List<AnimeDetails>>
    val searchAnimeFlow:StateFlow<List<AnimeDetails>>
    suspend fun requestNewAnime()

    suspend fun requestAnimeByName(query: String)
}