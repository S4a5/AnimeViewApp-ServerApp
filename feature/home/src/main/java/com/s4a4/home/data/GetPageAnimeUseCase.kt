package com.s4a4.home.data

import android.graphics.pdf.PdfDocument.Page
import com.s4a4.core.model.ktor.AnimeDetails
import com.s4a4.home.data.repository.GetPageAnimeRepository
import retrofit2.Response
import javax.inject.Inject

class GetPageAnimeUseCase @Inject constructor(private val getPageAnimeRepository: GetPageAnimeRepository) {
    suspend fun execute(page:Int = 1,count:Int = 1000): Response<List<AnimeDetails>> {
        return getPageAnimeRepository.getPage(page,count)
    }
}