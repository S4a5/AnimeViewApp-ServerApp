package com.example.home.data.anime_vost

import com.example.core.model.anilibria.getChanges.AnilibriaModel
import com.example.core.model.anilibria.getChanges.AnimeTitle
import com.example.core.model.anime_vost.AnimeVostModel
import com.example.home.data.anime_vost.repository.PageAnimeVostRepository
import com.example.home.data.anime_vost.repository.anilibria.PageAnilibriaRepository
import retrofit2.Response
import javax.inject.Inject

class GetPageFromAnimeVostUseCase @Inject constructor(
    private val repositoryAnimeVost: PageAnimeVostRepository,
    private val repositoryAnilibria: PageAnilibriaRepository
) {
    suspend fun execute(): Response<AnimeVostModel> {
        return repositoryAnimeVost.getPageAnime(1)
    }
    suspend fun execute1(): Response<AnilibriaModel> {
        return repositoryAnilibria.getPageAnime()
    }
}