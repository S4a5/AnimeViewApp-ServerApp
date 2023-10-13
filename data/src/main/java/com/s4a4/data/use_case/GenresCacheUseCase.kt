package com.s4a4.data.use_case

import com.s4a4.data.KtorService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenresCacheUseCase @Inject constructor(private val ktorService: KtorService) {

    private val _listGenreFlow = MutableStateFlow<List<String>>(emptyList())
    val listGenreFlow = _listGenreFlow.asStateFlow()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val response = ktorService.getAllGenres()
            if (response.isSuccessful ){
                response.body()?.let {
                    _listGenreFlow.emit(it)
                }
            }
        }
    }

}