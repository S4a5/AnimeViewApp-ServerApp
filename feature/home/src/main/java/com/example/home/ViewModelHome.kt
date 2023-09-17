package com.example.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.StateUi
import com.example.core.model.anilibria.getChanges.AnilibriaModel
import com.example.core.model.anilibria.getChanges.AnimeTitle
import com.example.core.model.anime_vost.AnimeVostModel
import com.example.home.data.anime_vost.GetGenreUseCase
import com.example.home.data.anime_vost.GetPageFromAnimeVostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class ViewModelHome @Inject constructor(private val getPageFromAnimeVostUseCase: GetPageFromAnimeVostUseCase,private val getGenreUseCase: GetGenreUseCase) :
    ViewModel() {

    private val _list = MutableStateFlow<StateUi<AnimeVostModel>>(StateUi.Loader)
    val list = _list.asStateFlow()

    private val _listAnilibria = MutableStateFlow<StateUi<AnilibriaModel>>(StateUi.Loader)
    val listAnilibria = _listAnilibria.asStateFlow()

    private val _genre = MutableStateFlow<StateUi<Map<String,String>>>(StateUi.Loader)
    val genre = _genre.asStateFlow()

    private val _selectGenre = mutableStateListOf<String>()
    val selectGenre : List<String> = _selectGenre

    init {
        viewModelScope.launch {
            getData()
            getData1()
            getGenre()
        }
    }
    fun onClickGenre(key: String) {
        if (!_selectGenre.remove(key)){
            _selectGenre.add(key)
        }
    }

    private suspend fun getData() {
        try {
            _list.emit(StateUi.Loader)
            val response = getPageFromAnimeVostUseCase.execute()
            if (response.isSuccessful) {
                _list.emit(StateUi.Success(data = response.body()))
            } else {
                if (response.errorBody() != null) {
                    _list.emit(StateUi.Failed(response.errorBody().toString()))
                } else {
                    _list.emit(StateUi.Failed(null))
                }
            }
        } catch (e: Exception) {
            _list.emit(StateUi.Failed(e.message))
        }
    }
private suspend fun getData1() {
    try {
        _listAnilibria.emit(StateUi.Loader)
        val response = getPageFromAnimeVostUseCase.execute1()
        if (response.isSuccessful) {
            _listAnilibria.emit(StateUi.Success(data = response.body()))
        } else {
            if (response.errorBody() != null) {
                _listAnilibria.emit(StateUi.Failed(response.errorBody().toString()))
            } else {
                _listAnilibria.emit(StateUi.Failed(null))
            }
        }
    } catch (e: Exception) {
        _listAnilibria.emit(StateUi.Failed(e.message))
    }
}

    private suspend fun getGenre() {
        try {
            _genre.emit(StateUi.Loader)
            val response = getGenreUseCase.execute()
            if (response.isSuccessful) {
                _genre.emit(StateUi.Success(data = response.body()))
            } else {
                if (response.errorBody() != null) {
                    _genre.emit(StateUi.Failed(response.errorBody().toString()))
                } else {
                    _genre.emit(StateUi.Failed(null))
                }
            }
        } catch (e: Exception) {
            _genre.emit(StateUi.Failed(e.message))
        }
    }
}