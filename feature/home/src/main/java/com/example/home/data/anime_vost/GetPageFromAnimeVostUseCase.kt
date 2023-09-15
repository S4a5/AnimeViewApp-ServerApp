package com.example.home.data.anime_vost

import com.example.core.model.anime_vost.AnimeVostModel
import com.example.home.data.anime_vost.repository.PageAnimeRepository
import javax.inject.Inject

class GetPageFromAnimeVostUseCase @Inject constructor(
    private val repository: PageAnimeRepository
) {
    suspend fun execute(): AnimeVostModel? {
        return repository.getPageAnime(1)
    }
}