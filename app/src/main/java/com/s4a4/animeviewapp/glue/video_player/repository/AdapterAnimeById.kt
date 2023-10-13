package com.s4a4.animeviewapp.glue.video_player.repository

import com.s4a4.core.model.ktor.AnimeDetails
import com.s4a4.data.use_case.LastDataCache
import com.s4a4.data.use_case.SearchDataCache
import com.s4a4.player.data.GetAnimeByIdRepository
import javax.inject.Inject

class AdapterAnimeById @Inject constructor(private val lastDataCache: LastDataCache, private val searchDataCache: SearchDataCache):
    GetAnimeByIdRepository {
    override fun getAnimeByIdRepository(animeId:Int): AnimeDetails {
        return   try {
            lastDataCache.getAnimeById(animeId)
        }catch (e:Throwable){
            searchDataCache.getAnimeById(animeId)
        }
    }

}