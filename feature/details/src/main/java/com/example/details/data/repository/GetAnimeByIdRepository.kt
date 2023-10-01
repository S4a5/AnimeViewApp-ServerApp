package com.example.details.data.repository

import com.example.core.model.ktor.AnimeDetails

interface GetAnimeByIdRepository {
    fun getAnimeByIdRepository(animeId:Int):AnimeDetails

}