package com.example.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import com.example.core.model.ktor.AnimeDetails
import com.example.details.data.repository.GetAnimeByIdRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ViewModelDetails @Inject constructor(private val getAnimeByIdRepository: GetAnimeByIdRepository) :ViewModel() {
    private val _anime = MutableStateFlow<AnimeDetails?>(null)
    val anime = _anime.asStateFlow()
    fun setAnimeId(animeId:Int){
        viewModelScope.launch {
            _anime.emit(getAnimeByIdRepository.getAnimeByIdRepository(animeId))
        }
    }
}