package com.s4a4.player

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s4a4.core.model.ktor.AnimeDetails
import com.s4a4.core.model.ktor.SeriesModel
import com.s4a4.player.data.GetAnimeByIdRepository
import com.s4a4.player.data.model.Definition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ViewModelVideoPlayer @Inject constructor(private val getAnimeByIdRepository: GetAnimeByIdRepository) :
    ViewModel() {

    private val _animeId = MutableStateFlow<Int?>(null)

    private val _selectVoice = MutableStateFlow<String?>(null)
    val selectVoice = _selectVoice.asStateFlow()

    private val _selectEpisodeId = MutableStateFlow<Int?>(null)
    val selectEpisodeId = _selectEpisodeId.asStateFlow()

//    private val _episodesCurrentVoice = MutableStateFlow<Map<Int,String>>(emptyMap())
//    val episodesCurrentVoice = _episodesCurrentVoice.asStateFlow()

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
//                    val a = listSeriesCurrentVoice.mapIndexed { index, seriesModel ->
//                        index to (seriesModel.name?:"null")
//                    }.toMap()
//
//                    _episodesCurrentVoice.emit(a)
                    val a = listSeriesCurrentVoice.find {
                        Log.d("qqqqqqqqqq26", it.id.toString())
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
                    Log.d("qqqqqqqqqq25", selectEpisodeId.toString())
                    Log.d("qqqqqqqqqq25", a.toString())

                    setAutoDefinition()
                    return@forEachIndexed
                }


            }
            if (animeDetails != null) {
                Log.d("qqqqqqqqq24", _listVoice.value.size.toString())
                Log.d("qqqqqqqqq24", _listSeriesCurrentVoice.value.size.toString())
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
        Log.d("qqqqqqqqqqq25",fhd.toString())
        Log.d("qqqqqqqqqqq25",hd.toString())
        Log.d("qqqqqqqqqqq25",std.toString())
        Log.d("qqqqqqqqqqq25","------")

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
}
