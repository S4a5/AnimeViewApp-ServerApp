package com.example.animeviewapp.glue.home.repository.anilibria

import com.example.core.model.anilibria.getChanges.AnilibriaModel
import com.example.core.model.anilibria.getChanges.AnimeTitle
import com.example.data.AnilibriaService
import com.example.home.data.anime_vost.repository.anilibria.PageAnilibriaRepository
import retrofit2.Response
import javax.inject.Inject

class AdapterPageAnilibriaRepository @Inject constructor(private val anilibriaService: AnilibriaService) :
    PageAnilibriaRepository {
    override suspend fun getPageAnime(): Response<AnilibriaModel> {
        return anilibriaService.getChanges()
    }


}