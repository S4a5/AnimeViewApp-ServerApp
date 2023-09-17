package com.example.home.data.anime_vost.repository

import retrofit2.Response

interface GenreRepository {
    suspend fun getGenre():Response<Map<String,String>>
}