package com.s4a4.home

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s4a4.core.MIN_LENGHT_SEARCH
import com.s4a4.core.model.AnimeSearchResult
import com.s4a4.core.model.StateUi
import com.s4a4.core.model.ktor.AnimeDetails
import com.s4a4.home.data.anime_vost.repository.PageAnimeRepository
import com.s4a4.home.data.repository.GenresRepository
import com.s4a4.home.navigate.NavigateHome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.junit.internal.Throwables
import java.io.IOException
import java.io.InterruptedIOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
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

    private val _stateUi = MutableStateFlow<StateUi<Nothing>>(StateUi.Loader)
    val stateUi = _stateUi.asStateFlow()

    private val _reFreshStateUi = MutableStateFlow<StateUi<Nothing>>(StateUi.Success())
    val reFreshStateUi = _reFreshStateUi.asStateFlow()

    private val _stateUiSearch = MutableStateFlow<StateUi<Nothing>>(StateUi.Success())
    val stateUiSearch = _stateUiSearch.asStateFlow()

    private val _viewList = MutableStateFlow<List<AnimeDetails>>(emptyList())
    val viewList = _viewList.asStateFlow()

    private val list = pageAnimeRepository.lastAnimeFlow
    private val searchList = pageAnimeRepository.searchAnimeFlow

    private val _progressNewAnime = MutableStateFlow<StateUi<String>>(StateUi.Success())
    val progressNewAnime = _progressNewAnime.asStateFlow()

    private val _genre = MutableStateFlow<StateUi<List<String>>>(StateUi.Loader)
    val genre = _genre.asStateFlow()

    private val _selectGenre = MutableStateFlow(mutableStateListOf<String>())
    val selectGenre = _selectGenre.asStateFlow()

    init {
        viewModelScope.launch {
//            while (true){
//                delay(100000)
//                getData()
//                getData()
//                getData()
//                getData()
//                refreshData()
//            }
        }
        genresRepository.listGenres.onEach {
            _genre.emit(StateUi.Success(it))
        }.launchIn(viewModelScope)
        genresRepository.listGenres.onEach {
            _genre.emit(StateUi.Success(it))
        }.launchIn(viewModelScope)
        getData()
        viewModelScope.launch {

            combine(
                list,
                searchList,
                _searchQuery,
                _listAnimeByGenres,
                _selectGenre
            ) { lastAnime, searchAnime, searchQuery, listAnimeByGenres, selectGenre ->
                val searchQueryTrim = searchQuery.trim()

                when {
                    searchQueryTrim.isNotBlank() && searchQueryTrim.length >= MIN_LENGHT_SEARCH -> {
                        _viewList.emit(value = searchAnime)
                    }

                    selectGenre.isNotEmpty() -> {
                        _viewList.emit(value = listAnimeByGenres)
                    }

                    searchQueryTrim.isBlank() -> {
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
                _stateUiSearch.emit(StateUi.Loader)
                try {
                    val result = pageAnimeRepository.requestAnimeByName(
                        _searchQuery.value.lowercase().trim()
                    )
                    when (result) {
                        is AnimeSearchResult.Success -> {
                            // Обработка успешного результата
                            _stateUiSearch.emit(StateUi.Success())
                        }

                        is AnimeSearchResult.Error -> {
                            // Обработка ошибки
                            _stateUiSearch.emit(StateUi.Failed(result.errorMessage))
                        }
                    }
                } catch (e: Exception) {
                    _stateUi.emit(StateUi.Failed(e.message))
                } catch (e: Throwable) {
                    _stateUi.emit(StateUi.Failed(e.message))
                }
            }
        }
    }

    fun onClickGenre(key: String) {
        if (!_selectGenre.value.remove(key)) {
            _selectGenre.value.add(key)
        }
        viewModelScope.launch {
            val response = genresRepository.getAnimeByGenres(_selectGenre.value)
            if (response.isSuccessful) {
                response.body()?.let {
                    _listAnimeByGenres.emit(it)
                }
            }

        }

    }

    fun getData() {
        viewModelScope.launch {
            if (_genre.value is StateUi.Success) {
                getGenre()
            }
            _stateUi.emit(StateUi.Loader)
            try {
                pageAnimeRepository.requestNewAnime().let {
                    _stateUi.emit(StateUi.Success())
                }
            } catch (e: Throwable) {
                _stateUi.emit(StateUi.Failed(e.message))
            }
        }
    }

    private fun getGenre() {

        viewModelScope.launch {
            try {
                genresRepository.loadGenreData()
            } catch (e: Exception) {
                _genre.emit(StateUi.Failed(e.message))
            }
        }
    }

    fun avtoLoadAnime() {

        viewModelScope.launch {
            val a = progressNewAnime.value as? StateUi.Success
            if ((a != null)
                && (a.data != "204")
                && _searchQuery.value.isBlank()
                && viewList.value.isNotEmpty()
            ) {
                _progressNewAnime.emit(StateUi.Loader)
                try {
                    pageAnimeRepository.requestNewAnime().let {
                        Log.d("insertDataInFlow", it.toString())
                        _progressNewAnime.emit(StateUi.Success(it))
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

    fun refreshData() {
        viewModelScope.launch {
            _reFreshStateUi.emit(StateUi.Loader)
            pageAnimeRepository.refreshData().let {
                _reFreshStateUi.emit(StateUi.Success())
            }
        }
    }
}