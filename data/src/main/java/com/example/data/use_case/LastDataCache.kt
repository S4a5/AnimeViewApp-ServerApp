package com.example.data.use_case

import android.util.Log
import com.example.core.model.StateUi
import com.example.core.model.ktor.AnimeDetails
import com.example.core.model.ktor.VoiceModel
import com.example.data.KtorService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LastDataCache @Inject constructor(private val ktorService: KtorService) {
    private val _lastAnimeFlow = MutableStateFlow<List<AnimeDetails>>(emptyList())
    val lastAnimeFlow = _lastAnimeFlow.asStateFlow()

    private var page = 1
    private val quantity = 10

    suspend fun requestNewAnime() {
        val response = ktorService.getPageAnime(page, quantity)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                _lastAnimeFlow.emit(_lastAnimeFlow.value + body)
                page++
                return
            }
        } else {
            val errorResponse = response.errorBody()
            if (errorResponse != null) {
                Throwable(errorResponse.string())
            }
        }
        throw Throwable("no information about the problem")
    }
    fun getAnimeById(animeId:Int):AnimeDetails{
        val animeDetails = _lastAnimeFlow.value.find { it.voiceModels.first().anime_id == animeId }
            return animeDetails ?: throw Throwable("no anime found by id")
    }
}
