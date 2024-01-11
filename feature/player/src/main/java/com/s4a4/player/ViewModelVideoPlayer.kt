package com.s4a4.player

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s4a4.core.model.ktor.AnimeDetails
import com.s4a4.core.model.ktor.SeriesModel
import com.s4a4.player.data.GetAnimeByIdRepository
import com.s4a4.player.data.model.Definition
import com.s4a4.player.navigate.NavigateVideoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ViewModelVideoPlayer @Inject constructor(private val getAnimeByIdRepository: GetAnimeByIdRepository,private val navigateVideoPlayer: NavigateVideoPlayer) :
    ViewModel() {

    private val _animeId = MutableStateFlow<Int?>(null)

    private val _selectVoice = MutableStateFlow<String?>(null)
    val selectVoice = _selectVoice.asStateFlow()

    private val _selectEpisodeId = MutableStateFlow<Int?>(null)
    val selectEpisodeId = _selectEpisodeId.asStateFlow()

    private val _animeDetails = MutableStateFlow<AnimeDetails?>(null)
    val animeDetails = _animeDetails.asStateFlow()

    private val _listSeriesCurrentVoice = MutableStateFlow(listOf<SeriesModel>())
    val listSeriesCurrentVoice = _listSeriesCurrentVoice.asStateFlow()

    private val _listVoice = MutableStateFlow(listOf<String>())
    val listVoice = _listVoice.asStateFlow()

    private val _currentDefinition = MutableStateFlow<Definition?>(null)
    val currentDefinition = _currentDefinition.asStateFlow()

    private val _currentEpisodeSeriesModel = MutableStateFlow<SeriesModel?>(null)
    val currentEpisodeSeriesModel = _currentEpisodeSeriesModel.asStateFlow()

    init {
        _animeId.onEach {
            if (it != null) {
                Log.d("qqqqqqqqq24", it.toString())
                _animeDetails.emit(getAnimeByIdRepository.getAnimeByIdRepository(it))
            }
        }.launchIn(viewModelScope)

        combine(
            _animeDetails,
            _selectVoice,
            _selectEpisodeId
        ) { animeDetails, selectVoice, selectEpisodeId ->
            Log.d("qqqqqqqqq24", animeDetails.toString())
            animeDetails?.voiceModels?.forEachIndexed { index, it ->
                _listVoice.emit(animeDetails.voiceModels.map { it.voiceGrupe })
                if (it.voiceGrupe == selectVoice) {

                    val listSeriesCurrentVoice = animeDetails.seriesModels[index]
                    _listSeriesCurrentVoice.emit(listSeriesCurrentVoice)
                    val a = listSeriesCurrentVoice.find {
                        it.id == selectEpisodeId
                    }
                    a?.let{
                        _currentEpisodeSeriesModel.emit(
                            it
                        )
                    }?: kotlin.run{
                        _selectEpisodeId.value = listSeriesCurrentVoice.size
                        _currentEpisodeSeriesModel.emit(
                            listSeriesCurrentVoice.last()
                        )

                    }

                    setAutoDefinition()
                    return@forEachIndexed
                }


            }
        }.launchIn(viewModelScope)
    }

    fun setArgument(id: Int, voice: String, episodeId: Int) {
        _selectEpisodeId.value = episodeId
        _selectVoice.value = voice
        _animeId.value = id


    }

    private fun setAutoDefinition() {
            val fhd = currentEpisodeSeriesModel.value?.fhd
            val hd = currentEpisodeSeriesModel.value?.hd
            val std = currentEpisodeSeriesModel.value?.std

            if (fhd != null) {
                _currentDefinition.value = Definition.FHD(fhd)
            } else if (hd != null) {
                _currentDefinition.value = Definition.HD(hd)
            } else if (std != null) {
                _currentDefinition.value = Definition.SD(std)
            } else {
                // TODO:
            }

    }

    fun setSelectVoice(item: String) {
        _selectVoice.value = item

    }

    fun setSelectEpisode(key: Int) {
        _selectEpisodeId.value = key
    }

    fun setSelectDefinition(definition: Definition) {
        _currentDefinition.value = definition
    }
    fun onBack(){
        navigateVideoPlayer.screenGetOut()
    }
}
