package com.example.animeviewapp.glue.home.di

import com.example.animeviewapp.glue.home.repository.AdapterGetPageAnimeRepository
import com.example.home.data.repository.GetPageAnimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface GetPageAnimeModule {
    @Binds
    fun bindsGetPageAnimeModule(adapter:AdapterGetPageAnimeRepository): GetPageAnimeRepository
}