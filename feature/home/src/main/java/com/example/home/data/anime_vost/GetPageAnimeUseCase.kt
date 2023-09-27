package com.example.home.data.anime_vost

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.core.model.ktor.AnimeDetails
import com.example.home.data.anime_vost.repository.PageAnimeRepository
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