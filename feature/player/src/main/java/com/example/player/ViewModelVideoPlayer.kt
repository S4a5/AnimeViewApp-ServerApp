package com.example.player

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import com.example.core.model.ktor.AnimeDetails
import com.example.core.model.ktor.SeriesModel
import com.example.player.data.GetAnimeByIdRepository
import com.example.player.data.model.Definition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ViewModelVideoPlayer @Inject constructor(private val getAnimeByIdRepository: GetAnimeByIdRepository) :
    ViewModel() {

    private val _animeId = MutableStateFlow<Int?>(null)
    private val _selectVoice = MutableStateFlow<String?>(null)
    private val _selectEpisode = MutableStateFlow<Int?>(null)

    private val _animeDetails = MutableStateFlow<AnimeDetails?>(null)

    private val _listSeriesCurrentVoice = MutableStateFlow(listOf<SeriesModel>())
    val listSeriesCurrentVoice = _listSeriesCurrentVoice.asStateFlow()

    private val _listVoice = MutableStateFlow(listOf<String>())
    val listVoice = _listVoice.asStateFlow()

    private val _definition = MutableStateFlow<Definition?>(null)
    val definition = _definition.asStateFlow()

    private val _currentEpisodeUrl = MutableStateFlow<SeriesModel?>(null)
    val currentEpisodeUrl = _currentEpisodeUrl.asStateFlow()

    init {
        _animeId.onEach {
            if (it != null) {
                _animeDetails.emit(getAnimeByIdRepository.getAnimeByIdRepository(it))
            }
        }.launchIn(viewModelScope)

        _animeDetails.onEach { animeDetails ->

            animeDetails?.voiceModels?.forEachIndexed { index, it ->
                if (it.voiceGrupe == _selectVoice.value) {

                    val listSeriesCurrentVoice = animeDetails.seriesModels[index]
                    _listSeriesCurrentVoice.emit(listSeriesCurrentVoice)
                    _currentEpisodeUrl.emit(
                        listSeriesCurrentVoice[_selectEpisode.value ?: 0]
                    )
                    setAutoDefinition(listSeriesCurrentVoice)
                    return@onEach
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setArgument(id: Int, voice: String, episode: Int) {
        Log.d("qqqqqqqqq24", voice)
        _selectVoice.value = voice
        _animeId.value = id

        _selectEpisode.value = episode
    }

    private fun setAutoDefinition(listSeriesCurrentVoice: List<SeriesModel>) {
        listSeriesCurrentVoice.forEach {
            val fhd = it.fhd
            val hd = it.hd
            val std = it.std

            if (fhd != null) {
                _definition.value = Definition.FHD(fhd)
            } else if (hd != null) {
                _definition.value = Definition.FHD(hd)
            } else if (std != null) {
                _definition.value = Definition.FHD(std)
            } else {
                // TODO:
            }
        }
    }
}
