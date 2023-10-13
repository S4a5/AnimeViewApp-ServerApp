package com.s4a4.animeviewapp.glue.sign_forget_password.di


import com.s4a4.animeviewapp.glue.sign_forget_password.navigate.AdapterSignForgetPasswordRoute
import com.s4a4.sign_forget_password.navigate.NavigateSignForgetPasswordRoute
import com.s4a4.sing_in.navigate.NavigateSignInRoute
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RouteSignForgetPasswordModule {
    @Binds
    fun bindsRouteSignForgetPasswordRoute(adapter: AdapterSignForgetPasswordRoute): NavigateSignForgetPasswordRoute
}