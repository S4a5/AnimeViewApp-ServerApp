package com.s4a4.animeviewapp.glue.home.repository

import com.s4a4.animeviewapp.glue.home.di.GenresModule
import com.s4a4.core.model.ktor.AnimeDetails
import com.s4a4.data.KtorService
import com.s4a4.data.use_case.GenresCacheUseCase
import com.s4a4.home.data.repository.GenresRepository
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response
import javax.inject.Inject

class GenresModuleAdapter @Inject constructor(private val genresCacheUseCase: GenresCacheUseCase,private val ktorService: KtorService) :
    GenresRepository {
    override val listGenres: StateFlow<List<String>>
        get() = genresCacheUseCase.listGenreFlow

    override suspend fun getAnimeByGenres(list: List<String>): Response<List<AnimeDetails>> {
        return ktorService.getAnimeByGenre(list)
    }
    override fun loadGenreData(){
        genresCacheUseCase.loadGenreData()
    }


}