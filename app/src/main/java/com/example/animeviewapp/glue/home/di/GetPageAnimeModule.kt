package com.example.animeviewapp.glue.home.di

import com.example.animeviewapp.glue.home.repository.AdapterGetPageAnime
import com.example.home.data.anime_vost.GetPageAnimeUseCase
import com.example.home.data.anime_vost.repository.PageAnimeRepository
import com.example.home.navigate.NavigateHome
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface GetPageAnimeModule {

    @Binds
    fun bindsPageAnime(adapter: AdapterGetPageAnime): PageAnimeRepository
}