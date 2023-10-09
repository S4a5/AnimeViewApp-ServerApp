package com.example.animeviewapp.glue.video_player.di


import com.example.animeviewapp.glue.video_player.repository.AdapterAnimeById
import com.example.player.data.GetAnimeByIdRepository
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