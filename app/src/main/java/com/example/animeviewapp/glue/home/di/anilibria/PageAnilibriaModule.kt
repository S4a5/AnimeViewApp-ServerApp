package com.example.animeviewapp.glue.home.di.anilibria

import com.example.animeviewapp.glue.home.repository.anilibria.AdapterPageAnilibriaRepository
import com.example.animeviewapp.glue.home.repository.anime_vost.AdapterGenresRepository
import com.example.animeviewapp.glue.home.repository.anime_vost.AdapterPageAnimeVostRepository
import com.example.home.data.anime_vost.repository.GenreRepository
import com.example.home.data.anime_vost.repository.anilibria.PageAnilibriaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface PageAnilibriaModule {
    @Binds
    fun bindsPageAnilibriaVost(adapter: AdapterPageAnilibriaRepository): PageAnilibriaRepository
}