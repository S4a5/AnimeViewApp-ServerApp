package com.s4a4.home

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s4a4.core.MIN_LENGHT_SEARCH
import com.s4a4.core.model.StateUi
import com.s4a4.core.model.ktor.AnimeDetails
import com.s4a4.home.data.anime_vost.repository.PageAnimeRepository
import com.s4a4.home.data.repository.GenresRepository
import com.s4a4.home.navigate.NavigateHome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class ViewModelHome @Inject constructor(
    private val pageAnimeRepository: PageAnimeRepository,
    private val navigateHome: NavigateHome,
    private val genresRepository: GenresRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()


    private val _listAnimeByGenres = MutableStateFlow<List<AnimeDetails>>(emptyList())
    val listAnimeByGenres = _listAnimeByGenres.asStateFlow()

    private val _stateUi = MutableStateFlow<StateUi<Nothing>>(StateUi.Loader)
    val stateUi = _stateUi.asStateFlow()

    private val _viewList = MutableStateFlow<List<AnimeDetails>>(emptyList())
    val viewList = _viewList.asStateFlow()

    private val list = pageAnimeRepository.lastAnimeFlow
    private val searchList = pageAnimeRepository.searchAnimeFlow

    private val _progressNewAnime = MutableStateFlow<StateUi<Nothing>>(StateUi.Success())
    val progressNewAnime = _progressNewAnime.asStateFlow()

    private val _genre = MutableStateFlow<StateUi<List<String>>>(StateUi.Loader)
    val genre = _genre.asStateFlow()

    private val _selectGenre = mutableStateListOf<String>()
    val selectGenre: List<String> = _selectGenre

    init {
        genresRepository.listGenres.onEach {
            _genre.emit(StateUi.Success(it))
        }.launchIn(viewModelScope)
        viewModelScope.launch {
            getData()
            combine(list, searchList, _searchQuery,listAnimeByGenres) { lastAnime, searchAnime, searchQuery,listAnimeByGenres ->
                val searchQueryTrim = searchQuery.trim()

                when{
                    searchQueryTrim.isNotBlank() && searchQueryTrim.length >= MIN_LENGHT_SEARCH ->{
                        _viewList.emit(value = searchAnime)
                    }
                    listAnimeByGenres.isNotEmpty() ->{
                        _viewList.emit(value = listAnimeByGenres)
                    }
                    searchQueryTrim.isBlank() ->{
                        _viewList.emit(value = lastAnime)
                    }


                }

            }.launchIn(viewModelScope)

        }

    }

    fun setSearchQuery(searchQuery: String) {
        viewModelScope.launch {
            _searchQuery.value = searchQuery
            val searchQueryTrim = searchQuery.trim()
            if (searchQueryTrim.length >= MIN_LENGHT_SEARCH) {
                try {
                    pageAnimeRepository.requestAnimeByName(_searchQuery.value.lowercase().trim())
                } catch (e: Exception) {
                    _stateUi.emit(StateUi.Failed(e.message))
                } catch (e: Throwable) {
                    _stateUi.emit(StateUi.Failed(e.message))
                }
            }
        }
    }

    fun onClickGenre(key: String) {
        if (!_selectGenre.remove(key)) {
            _selectGenre.add(key)
        }
        viewModelScope.launch {
            val response = genresRepository.getAnimeByGenres(_selectGenre)
            if (response.isSuccessful){
                response.body()?.let {
                    _listAnimeByGenres.emit(it)
                }
            }

        }

    }

    private suspend fun getData() {
        _stateUi.emit(StateUi.Loader)
        try {
            pageAnimeRepository.requestNewAnime().let {
                _stateUi.emit(StateUi.Success())
            }
        } catch (e: Exception) {
            _stateUi.emit(StateUi.Failed(e.message))
        } catch (e: Throwable) {
            _stateUi.emit(StateUi.Failed(e.message))
        }

    }

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
        viewModelScope.launch {
            if (progressNewAnime.value is StateUi.Success && _searchQuery.value.isBlank() && viewList.value.isNotEmpty()) {
                _progressNewAnime.emit(StateUi.Loader)
                try {
                    pageAnimeRepository.requestNewAnime().let {

                        _progressNewAnime.emit(StateUi.Success())


                    }
                } catch (e: Exception) {
                    _progressNewAnime.emit(StateUi.Failed(e.message))
                } catch (e: Throwable) {
                    _progressNewAnime.emit(StateUi.Failed(e.message))
                }
            }

        }
    }

    fun selectAnime(animeId: Int) {
        navigateHome.navigateToDetails(animeId)
    }
}