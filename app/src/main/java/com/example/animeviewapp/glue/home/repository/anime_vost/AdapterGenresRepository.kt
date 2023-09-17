package com.example.animeviewapp.glue.home.repository.anime_vost

import com.example.data.AnimeVostService
import com.example.home.data.anime_vost.repository.GenreRepository
import retrofit2.Response
import javax.inject.Inject

class AdapterGenresRepository @Inject constructor(private val animeVostService: AnimeVostService) :
    GenreRepository {
    override suspend fun getGenre(): Response<Map<String, String>> {
        return animeVostService.getGenres()
    }

}