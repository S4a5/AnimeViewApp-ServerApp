package com.example.animeviewapp.glue.home.repository

import com.example.core.model.StateUi
import com.example.core.model.ktor.AnimeDetails
import com.example.data.KtorService
import com.example.data.use_case.LastDataCache
import com.example.home.data.anime_vost.repository.PageAnimeRepository
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response
import javax.inject.Inject

class AdapterGetPageAnime @Inject constructor(private val lastDataCache: LastDataCache):PageAnimeRepository {
    override val lastAnimeFlow: StateFlow<List<AnimeDetails>>
        get() = lastDataCache.lastAnimeFlow

    override suspend fun requestNewAnime() {
        return lastDataCache.requestNewAnime()
    }

}