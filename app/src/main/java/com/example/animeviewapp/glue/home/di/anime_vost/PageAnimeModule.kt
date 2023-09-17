package com.example.animeviewapp.glue.home.di.anime_vost

import com.example.animeviewapp.glue.home.repository.anime_vost.AdapterPageAnimeVostRepository
import com.example.home.data.anime_vost.repository.PageAnimeVostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface PageAnimeModule {

    @Binds
    fun bindsPageAnimeModule(adapter: AdapterPageAnimeVostRepository): PageAnimeVostRepository
}