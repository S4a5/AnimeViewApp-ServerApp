package com.example.details

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import com.example.core.model.ktor.AnimeDetails
import com.example.core.model.ktor.SeriesModel
import com.example.details.data.repository.GetAnimeByIdRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelDetails @Inject constructor(private val getAnimeByIdRepository: GetAnimeByIdRepository) :
    ViewModel() {

    private val _seriesText = MutableStateFlow("")
    val seriesText = _seriesText.asStateFlow()

    private val _anime = MutableStateFlow<AnimeDetails?>(null)
    val anime = _anime.asStateFlow()

    private val _selectViewVoice = MutableStateFlow<Int>(0)
//    val selectViewVoice = _selectViewVoice.asStateFlow()

    private val _listSeries = MutableStateFlow<List<SeriesModel>>(emptyList())
    val listSeries = _listSeries.asStateFlow()

    private val _nameModel = MutableStateFlow(anime.value?.nameModels?.get(_selectViewVoice.value))
    private val _voiceModel =
        MutableStateFlow(anime.value?.voiceModels?.get(_selectViewVoice.value))

    val nameModel = _nameModel.asStateFlow()
    val voiceModel = _voiceModel.asStateFlow()

    private val _scrollAnime = MutableStateFlow(0)
    val scrollAnime = _scrollAnime.asStateFlow()

    init {
        _anime.onEach {
            var list = mutableStateListOf<SeriesModel>()

            it?.seriesModels?.forEach {
                if (it.size > list.size) {
                    list = it.toMutableStateList()
                }
            }

            _listSeries.emit(list)
        }.launchIn(viewModelScope)
        _seriesText.onEach {
            _listSeries.value.forEachIndexed { index, seriesModel ->
                if (seriesModel.name == it) {
                    _scrollAnime.value = index
                    return@forEachIndexed
                }
            }
        }.launchIn(viewModelScope)
    }


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

    fun setSeriesText(it: String) {
        _seriesText.value = it
    }
}