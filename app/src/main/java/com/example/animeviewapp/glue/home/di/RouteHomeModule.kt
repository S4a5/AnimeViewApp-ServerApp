package com.example.animeviewapp.glue.home.di

import com.example.animeviewapp.glue.home.navigate.AdapterHomeRoute
import com.example.animeviewapp.glue.sign_in.navigate.AdapterSignInRoute
import com.example.home.navigate.NavigateHome

import com.example.sing_in.navigate.NavigateSignInRoute
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