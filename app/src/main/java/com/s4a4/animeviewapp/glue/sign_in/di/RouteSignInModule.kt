package com.s4a4.animeviewapp.glue.sign_in.di


import com.s4a4.animeviewapp.glue.sign_in.navigate.AdapterSignInRoute

import com.s4a4.sing_in.navigate.NavigateSignInRoute
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RouteSignInModule {
    @Binds
    fun bindsRouteSignIn(adapterSignInRoute: AdapterSignInRoute): NavigateSignInRoute
}