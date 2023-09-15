package com.example.animeviewapp.glue.sign_up.di

import com.example.animeviewapp.glue.sign_up.navigate.AdapterSignUpRoute
import com.example.sign_up.navigate.NavigateSignUpRoute
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RouteSignUpModule {
    @Binds
    fun bindsRouteSignUp(adapterSignUpRoute: AdapterSignUpRoute): NavigateSignUpRoute
}