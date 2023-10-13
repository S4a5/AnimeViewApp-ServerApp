package com.s4a4.player.data

import com.s4a4.core.model.ktor.AnimeDetails

interface GetAnimeByIdRepository {
    fun getAnimeByIdRepository(animeId:Int):AnimeDetails

}