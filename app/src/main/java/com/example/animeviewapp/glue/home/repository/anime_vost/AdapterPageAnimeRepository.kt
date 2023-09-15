package com.example.animeviewapp.glue.home.repository.anime_vost

import com.example.core.model.anime_vost.AnimeVostModel
import com.example.data.MyApiService
import com.example.home.data.anime_vost.repository.PageAnimeRepository
import javax.inject.Inject

class AdapterPageAnimeRepository @Inject constructor(private val myApiService: MyApiService) :
    PageAnimeRepository {
    override suspend fun getPageAnime(
        page: Int,
        quantity: Int
    ): AnimeVostModel? {
//        myApiService.getPageAnime(page)
        return null
    }

}