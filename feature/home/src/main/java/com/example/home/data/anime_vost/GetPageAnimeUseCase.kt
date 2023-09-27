package com.example.home.data.anime_vost

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.core.model.AnimeDetails
import com.example.core.model.ItemAnimeModel
import com.example.core.model.StateUi
import com.example.home.data.anime_vost.repository.PageAnimeRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class GetPageAnimeUseCase @Inject constructor(private val pageAnimeRepository: PageAnimeRepository) {

    private val allAnimeList = mutableStateListOf<AnimeDetails>()
    private var page = 1
    private val quantity = 200
    suspend fun nextPage(): SnapshotStateList<AnimeDetails> {
        val request = pageAnimeRepository.getPageAnime(page,quantity)
        if (request.isSuccessful){
            val result = request.body()
            if (result != null){
                allAnimeList.addAll(result)
            }else{

            }

        }else{

        }

        return allAnimeList
    }
}