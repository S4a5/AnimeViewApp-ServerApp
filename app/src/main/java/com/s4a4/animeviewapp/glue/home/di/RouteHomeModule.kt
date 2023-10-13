package com.s4a4.animeviewapp.glue.home.di

import com.s4a4.animeviewapp.glue.home.navigate.AdapterHomeRoute
import com.s4a4.animeviewapp.glue.sign_in.navigate.AdapterSignInRoute
import com.s4a4.home.navigate.NavigateHome

import com.s4a4.sing_in.navigate.NavigateSignInRoute
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RouteHomeModule {
    @Binds
    fun bindsRouteSignIn(adapter: AdapterHomeRoute): NavigateHome
}