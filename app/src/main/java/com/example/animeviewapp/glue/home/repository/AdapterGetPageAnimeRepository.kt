package com.example.animeviewapp.glue.home.repository

import com.example.core.model.ktor.AnimeDetails
import com.example.data.KtorService
import com.example.home.data.repository.GetPageAnimeRepository
import retrofit2.Response
import javax.inject.Inject

class AdapterGetPageAnimeRepository @Inject constructor(private val ktorService: KtorService):GetPageAnimeRepository {
    override suspend fun getPage(page: Int, count: Int): Response<List<AnimeDetails>> {
        return ktorService.getPageAnime(page,count)
    }
}