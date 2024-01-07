package com.s4a4.data.use_case

import android.util.Log
import com.s4a4.core.model.AnimeSearchResult
import com.s4a4.core.model.ktor.AnimeDetails
import com.s4a4.data.KtorService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class SearchDataCache @Inject constructor(private val ktorService: KtorService) {
    private val _searchAnimeFlow = MutableStateFlow<List<AnimeDetails>>(emptyList())
    val searchAnimeFlow = _searchAnimeFlow.asStateFlow()

    suspend fun requestAnimeByName(query: String): AnimeSearchResult {
        try {
            val response = ktorService.getAnimeByName(query)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    _searchAnimeFlow.emit(body)
                    return AnimeSearchResult.Success
                }
            } else {
                val errorResponse = response.errorBody()
                if (errorResponse != null) {
                    val errorMessage = errorResponse.string()
                    return AnimeSearchResult.Error(errorMessage)
                }
            }
        } catch (e: Exception) {
            return AnimeSearchResult.Error("Network error occurred.")
        }

        return AnimeSearchResult.Error("No information about the problem.")
    }
    fun getAnimeById(animeId:Int):AnimeDetails{
        val animeDetails = _searchAnimeFlow.value.find {
            it.voiceModels.first().anime_id == animeId }
        return animeDetails ?: throw Throwable("no anime found by id")
    }
}