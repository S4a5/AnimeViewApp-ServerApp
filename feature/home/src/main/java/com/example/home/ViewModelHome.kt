package com.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.anime_vost.AnimeVostModel
import com.example.home.data.anime_vost.GetPageFromAnimeVostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelHome @Inject constructor(private val getPageFromAnimeVostUseCase: GetPageFromAnimeVostUseCase) : ViewModel() {

    private val _list = MutableStateFlow<AnimeVostModel?>(null)
    val list = _list.asStateFlow()
    init {
        viewModelScope.launch {
            _list.emit(getPageFromAnimeVostUseCase.execute())
        }
    }
}