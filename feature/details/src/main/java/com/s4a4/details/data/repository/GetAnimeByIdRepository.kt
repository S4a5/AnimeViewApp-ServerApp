package com.s4a4.details.data.repository

import com.s4a4.core.model.ktor.AnimeDetails

interface GetAnimeByIdRepository {
    fun getAnimeByIdRepository(animeId:Int):AnimeDetails

}