package com.example.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.core.model.ktor.AnimeDetails
import com.example.details.data.repository.GetAnimeByIdRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
@HiltViewModel
class ViewModelDetails @Inject constructor(private val getAnimeByIdRepository: GetAnimeByIdRepository, private val animeId:Int) :ViewModel() {
    private val _anime = MutableStateFlow<AnimeDetails>(getAnimeByIdRepository.getAnimeByIdRepository(animeId))
    val anime = _anime.asStateFlow()

}
//@ViewModelScoped
//class ViewModelDetailsFactory @Inject constructor(private val getAnimeByIdRepository: GetAnimeByIdRepository) : ViewModelStoreOwner {
//    private var animeId: Int? = 0 // Исходное значение может быть любым, вам нужно будет его установить перед созданием ViewModel
//
//    fun initialize(animeId: Int) {
//        this.animeId = animeId
//    }
//
////     fun <T : ViewModel?> create(modelClass: Class<T>): T {
////        if (modelClass.isAssignableFrom(ViewModelDetails::class.java)) {
////            return ViewModelDetails(getAnimeByIdRepository, animeId!!) as T
////        }
////        throw IllegalArgumentException("Unknown ViewModel class")
////    }
//}