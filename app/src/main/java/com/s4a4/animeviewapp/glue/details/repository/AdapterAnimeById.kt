package com.s4a4.animeviewapp.glue.details.repository

import com.s4a4.core.model.ktor.AnimeDetails
import com.s4a4.data.use_case.LastDataCache
import com.s4a4.data.use_case.SearchDataCache
import com.s4a4.details.data.repository.GetAnimeByIdRepository
import com.s4a4.home.data.repository.GetPageAnimeRepository
import retrofit2.Response
import javax.inject.Inject

class AdapterAnimeById @Inject constructor(private val lastDataCache: LastDataCache, private val searchDataCache: SearchDataCache):GetAnimeByIdRepository {
    override fun getAnimeByIdRepository(animeId:Int): AnimeDetails {
        return   try {
            lastDataCache.getAnimeById(animeId)
        }catch (e:Throwable){
            searchDataCache.getAnimeById(animeId)
        }
    }

}