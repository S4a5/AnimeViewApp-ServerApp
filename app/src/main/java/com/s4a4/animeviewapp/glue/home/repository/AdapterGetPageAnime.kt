package com.s4a4.animeviewapp.glue.home.repository

import com.s4a4.core.model.AnimeSearchResult
import com.s4a4.core.model.ktor.AnimeDetails
import com.s4a4.data.use_case.LastDataCache
import com.s4a4.data.use_case.SearchDataCache
import com.s4a4.home.data.anime_vost.repository.PageAnimeRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AdapterGetPageAnime @Inject constructor(private val lastDataCache: LastDataCache,private val searchDataCache: SearchDataCache):PageAnimeRepository {
    override val lastAnimeFlow: StateFlow<List<AnimeDetails>>
        get() = lastDataCache.lastAnimeFlow
    override val searchAnimeFlow: StateFlow<List<AnimeDetails>>
        get() = searchDataCache.searchAnimeFlow
    override suspend fun requestNewAnime(): String? {
        return lastDataCache.requestNewAnime()
    }
    override suspend fun requestAnimeByName(query: String): AnimeSearchResult {
        return searchDataCache.requestAnimeByName(query)
    }

    override suspend fun refreshData() {
        lastDataCache.refreshData()
    }
}