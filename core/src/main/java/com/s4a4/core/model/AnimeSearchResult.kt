package com.s4a4.core.model

import com.s4a4.core.model.ktor.AnimeDetails

sealed class AnimeSearchResult {
    object Success : AnimeSearchResult()

    data class Error(val errorMessage: String) : AnimeSearchResult()
}