package com.example.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.StateUi
import com.example.home.data.anime_vost.repository.PageAnimeRepository
import com.example.home.navigate.NavigateHome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class ViewModelHome @Inject constructor(private val pageAnimeRepository: PageAnimeRepository,private val navigateHome: NavigateHome) :
    ViewModel() {

    private val _stateUi = MutableStateFlow<StateUi<Nothing>>(StateUi.Loader)
    val stateUi = _stateUi.asStateFlow()
    val list = pageAnimeRepository.lastAnimeFlow

    private val _progressNewAnime = MutableStateFlow<StateUi<Nothing>>(StateUi.Loader)
    val progressNewAnime = _progressNewAnime.asStateFlow()

    private val _genre = MutableStateFlow<StateUi<Map<String, String>>>(StateUi.Loader)
    val genre = _genre.asStateFlow()

    private val _selectGenre = mutableStateListOf<String>()
    val selectGenre: List<String> = _selectGenre

    init {
        viewModelScope.launch {
            getData()
//            getGenre()
        }
    }

    fun onClickGenre(key: String) {
        if (!_selectGenre.remove(key)) {
            _selectGenre.add(key)
        }
    }
    private suspend fun getData(){
        _stateUi.emit(StateUi.Loader)
        try {
            pageAnimeRepository.requestNewAnime().let {
                _stateUi.emit(StateUi.Success())
            }
        }catch (e:Exception){
            _stateUi.emit(StateUi.Failed(e.message))
        }

    }
//    private suspend fun getData() {
//        try {
//            val response = pageAnimeRepository.
//            _list.emit(StateUi.Success(response))
//        } catch (e: Exception) {
//            _list.emit(StateUi.Failed(e.message.toString()))
//        }
//    }

//    private suspend fun getGenre() {
//        try {
//            _genre.emit(StateUi.Loader)
//            val response = getGenreUseCase.execute()
//            if (response.isSuccessful) {
//                _genre.emit(StateUi.Success(data = response.body()))
//            } else {
//                if (response.errorBody() != null) {
//                    _genre.emit(StateUi.Failed(response.errorBody().toString()))
//                } else {
//                    _genre.emit(StateUi.Failed(null))
//                }
//            }
//        } catch (e: Exception) {
//            _genre.emit(StateUi.Failed(e.message))
//        }
//    }

    fun avtoLoadAnime() {
//        viewModelScope.launch {
//            if (_list.value is StateUi.Success){
//                _progressNewAnime.emit(StateUi.Loader)
//                val response = getPageFromAnimeVostUseCase.nextPage()
//                _list.emit(StateUi.Success(response))
//                _progressNewAnime.emit(StateUi.Success())
//            }
//
//        }
    }

    fun selectAnime(animeId: Int) {
        navigateHome.navigateToDetails(animeId)
    }
}