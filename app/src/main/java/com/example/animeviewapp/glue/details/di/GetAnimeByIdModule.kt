package com.example.animeviewapp.glue.details.di

import com.example.animeviewapp.glue.details.repository.AdapterAnimeById
import com.example.animeviewapp.glue.home.repository.AdapterGetPageAnime
import com.example.details.data.repository.GetAnimeByIdRepository
import com.example.home.data.anime_vost.repository.PageAnimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface GetAnimeByIdModule {
    @Binds
    fun bindsGetAnimeByIdModule(adapter: AdapterAnimeById): GetAnimeByIdRepository
}