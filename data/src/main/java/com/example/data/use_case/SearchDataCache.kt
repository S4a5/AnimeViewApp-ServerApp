package com.example.data.use_case

import com.example.core.model.ktor.AnimeDetails
import com.example.data.KtorService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchDataCache @Inject constructor(private val ktorService: KtorService) {
    private val _searchAnimeFlow = MutableStateFlow<List<AnimeDetails>>(emptyList())
    val searchAnimeFlow = _searchAnimeFlow.asStateFlow()

    suspend fun requestAnimeByName(query: String){
        val response = ktorService.getAnimeByName(query)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                _searchAnimeFlow.emit(body)
                return
            }
        }else{
            val errorResponse = response.errorBody()
            if (errorResponse != null) {
                Throwable(errorResponse.string())
            }
        }
        throw Throwable("no information about the problem")
    }
    fun getAnimeById(animeId:Int):AnimeDetails{
        val animeDetails = _searchAnimeFlow.value.find { it.voiceModels.first().anime_id == animeId }
        return animeDetails ?: throw Throwable("no anime found by id")
    }
}