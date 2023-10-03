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
class ViewModelDetails @Inject constructor(private val getAnimeByIdRepository: GetAnimeByIdRepository) :
    ViewModel() {
    private val _anime = MutableStateFlow<AnimeDetails?>(null)
    val anime = _anime.asStateFlow()

    private val _selectViewVoice = MutableStateFlow<Int>(0)
//    val selectViewVoice = _selectViewVoice.asStateFlow()

    private val _nameModel = MutableStateFlow(anime.value?.nameModels?.get(_selectViewVoice.value))
    private val _voiceModel =
        MutableStateFlow(anime.value?.voiceModels?.get(_selectViewVoice.value))

    val nameModel = _nameModel.asStateFlow()
    val voiceModel = _voiceModel.asStateFlow()
    fun setAnimeId(animeId: Int) {
        viewModelScope.launch {
            _anime.emit(getAnimeByIdRepository.getAnimeByIdRepository(animeId))
            setSelectViewVoice(0)
        }
    }

    fun setSelectViewVoice(it: Int) {
        viewModelScope.launch {
            _selectViewVoice.value = it
            _nameModel.emit(anime.value?.nameModels?.get(_selectViewVoice.value))
            _voiceModel.emit(anime.value?.voiceModels?.get(_selectViewVoice.value))
        }
    }


}