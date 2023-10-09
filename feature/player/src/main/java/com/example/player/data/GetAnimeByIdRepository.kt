package com.example.player.data

import com.example.core.model.ktor.AnimeDetails

interface GetAnimeByIdRepository {
    fun getAnimeByIdRepository(animeId:Int):AnimeDetails

}