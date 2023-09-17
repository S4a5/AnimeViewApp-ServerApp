package com.example.home.data.anime_vost

import com.example.home.data.anime_vost.repository.GenreRepository
import retrofit2.Response
import javax.inject.Inject

class GetGenreUseCase@Inject constructor(private val repository: GenreRepository) {
    suspend fun execute(): Response<Map<String, String>> {
        return repository.getGenre()
    }
}