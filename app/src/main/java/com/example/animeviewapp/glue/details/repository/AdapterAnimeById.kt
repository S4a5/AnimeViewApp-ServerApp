package com.example.animeviewapp.glue.details.repository

import com.example.core.model.ktor.AnimeDetails
import com.example.data.use_case.LastDataCache
import com.example.data.use_case.SearchDataCache
import com.example.details.data.repository.GetAnimeByIdRepository
import com.example.home.data.repository.GetPageAnimeRepository
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