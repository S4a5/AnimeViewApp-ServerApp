package com.example.details

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.ktor.AnimeDetails
import com.example.core.model.ktor.SeriesModel
import com.example.details.data.repository.GetAnimeByIdRepository
import com.example.details.navigation.NavigationDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelDetails @Inject constructor(
    private val getAnimeByIdRepository: GetAnimeByIdRepository,
    private val navigationDetails: NavigationDetails
) :
    ViewModel() {

    private val _seriesText = MutableStateFlow("")
    val seriesText = _seriesText.asStateFlow()

    private val _anime = MutableStateFlow<AnimeDetails?>(null)
    val anime = _anime.asStateFlow()

    private val _selectViewVoice = MutableStateFlow<Int>(0)

    private val _listSeries = MutableStateFlow<List<SeriesModel>>(emptyList())
    val listSeries = _listSeries.asStateFlow()

    private val _nameModel = MutableStateFlow(anime.value?.nameModels?.get(_selectViewVoice.value))
    private val _voiceModel =
        MutableStateFlow(anime.value?.voiceModels?.get(_selectViewVoice.value))

    val nameModel = _nameModel.asStateFlow()
    val voiceModel = _voiceModel.asStateFlow()

    private val _focusEpisode = MutableStateFlow(false)
    val focusEpisode = _focusEpisode.asStateFlow()

    private val _scrollAnime = MutableStateFlow(0)
    val scrollAnime = _scrollAnime.asStateFlow()

    private val _animeId = mutableIntStateOf(0)

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
            Log.d("animeId11111",animeId.toString())
            _anime.emit(getAnimeByIdRepository.getAnimeByIdRepository(animeId))
            setSelectViewVoice(0)
            _animeId.intValue = animeId
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

    fun onFocusEpisode(focused: Boolean) {
        _focusEpisode.value = focused
    }

    fun onClickSeries(seriesModel: SeriesModel, indexSeries: Int) {
        val voiceModel = _anime.value?.voiceModels?.find {
            seriesModel.voice_id == it.id
        }
        if (voiceModel != null) {
            navigationDetails.openVideoPlayer(
                _animeId.intValue,
                voiceModel.voiceGrupe,
                indexSeries
            )
        }
    }
}