package com.example.home.data

import android.graphics.pdf.PdfDocument.Page
import com.example.core.model.ktor.AnimeDetails
import com.example.home.data.repository.GetPageAnimeRepository
import retrofit2.Response
import javax.inject.Inject

class GetPageAnimeUseCase @Inject constructor(private val getPageAnimeRepository: GetPageAnimeRepository) {
    suspend fun execute(page:Int = 1,count:Int = 1000): Response<List<AnimeDetails>> {
        return getPageAnimeRepository.getPage(page,count)
    }
}