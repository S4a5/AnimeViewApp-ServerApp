package com.example.animeviewapp.glue.home.repository.anime_vost

import com.example.core.model.anime_vost.AnimeVostModel
import com.example.data.AnimeVostService
import com.example.home.data.anime_vost.repository.PageAnimeVostRepository
import retrofit2.Response
import javax.inject.Inject

class AdapterPageAnimeVostRepository @Inject constructor(private val animeVostService: AnimeVostService) :
    PageAnimeVostRepository {
    override suspend fun getPageAnime(
        page: Int,
        quantity: Int
    ): Response<AnimeVostModel> {
        return animeVostService.getPageAnime(page)
    }

}