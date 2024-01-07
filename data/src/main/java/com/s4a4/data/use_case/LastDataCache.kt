package com.s4a4.data.use_case

import android.accounts.NetworkErrorException
import android.media.MediaDrm.ErrorCodes
import android.util.Log
import com.s4a4.core.model.StateUi
import com.s4a4.core.model.ktor.AnimeDetails
import com.s4a4.core.model.ktor.VoiceModel
import com.s4a4.data.KtorService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LastDataCache @Inject constructor(private val ktorService: KtorService) {
    private val _lastAnimeFlow = MutableStateFlow<List<AnimeDetails>>(emptyList())
    val lastAnimeFlow = _lastAnimeFlow.asStateFlow()

    private var page = 1
    private val quantity = 10

    suspend fun requestNewAnime(): String? {
        val response = ktorService.getPageAnime(page, quantity)
        return insertDataInFlow(response)
    }

    fun getAnimeById(animeId: Int): AnimeDetails {
        val animeDetails = _lastAnimeFlow.value.find {
            it.voiceModels.first().anime_id == animeId
        }
        return animeDetails ?: throw Throwable("no anime found by id")
    }

    suspend fun refreshData() {

        val response = ktorService.getNewLastList(1, quantity)
        Log.d("refreshData","qweqweqe")
        page = 1
        if (response.isSuccessful) {
            Log.d("refreshData","1231231")

            _lastAnimeFlow.emit(emptyList())
            insertDataInFlow(response)
        }


    }

    private suspend fun insertDataInFlow(response: Response<List<AnimeDetails>>): String? {
        return if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                _lastAnimeFlow.emit(_lastAnimeFlow.value + body)
                page++
                return null
            } else {

                return response.code().toString()
            }
        } else {
            val errorResponse = response.errorBody()
//            if (errorResponse != null) {
            throw Throwable(errorResponse?.string())
//            }
        }
    }
}
