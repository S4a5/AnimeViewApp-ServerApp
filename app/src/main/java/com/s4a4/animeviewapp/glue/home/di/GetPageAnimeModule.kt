package com.s4a4.animeviewapp.glue.home.di

import com.s4a4.animeviewapp.glue.home.repository.AdapterGetPageAnime
import com.s4a4.home.data.anime_vost.repository.PageAnimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface GetPageAnimeModule {
    @Binds
    fun bindsGetPageAnimeModule(adapter: AdapterGetPageAnime): PageAnimeRepository
}