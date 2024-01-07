package com.s4a4.home.data.repository

import com.s4a4.core.model.ktor.AnimeDetails
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response

interface GenresRepository {

    val listGenres:StateFlow<List<String>>

    suspend fun getAnimeByGenres(list: List<String>): Response<List<AnimeDetails>>
    fun loadGenreData()
}