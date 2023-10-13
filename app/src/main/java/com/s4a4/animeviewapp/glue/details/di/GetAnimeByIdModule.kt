package com.s4a4.animeviewapp.glue.details.di

import com.s4a4.animeviewapp.glue.details.repository.AdapterAnimeById
import com.s4a4.animeviewapp.glue.home.repository.AdapterGetPageAnime
import com.s4a4.details.data.repository.GetAnimeByIdRepository
import com.s4a4.home.data.anime_vost.repository.PageAnimeRepository
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