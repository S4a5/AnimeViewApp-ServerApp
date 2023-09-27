package com.example.animeviewapp.glue.home.repository

import com.example.core.model.AnimeDetails
import com.example.data.KtorService
import com.example.home.data.anime_vost.repository.PageAnimeRepository
import retrofit2.Response
import javax.inject.Inject

class AdapterGetPageAnime @Inject constructor(private val ktorService: KtorService):PageAnimeRepository {
    override suspend fun getPageAnime(page: Int, quantity: Int): Response<List<AnimeDetails>> {
        return ktorService.getPageAnime(page,quantity)
    }
}