package com.s4a4.animeviewapp.glue.video_player.di

import com.s4a4.animeviewapp.glue.home.navigate.AdapterHomeRoute
import com.s4a4.animeviewapp.glue.video_player.navigation.AdapterVideoPlayerRoute
import com.s4a4.home.navigate.NavigateHome
import com.s4a4.player.navigate.NavigateVideoPlayer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RouteVideoPlayerModule {
    @Binds
    fun bindsRouteVideoPlayer(adapter: AdapterVideoPlayerRoute): NavigateVideoPlayer
}