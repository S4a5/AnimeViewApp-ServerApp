package com.example.animeviewapp.glue.sign_forget_password.di


import com.example.animeviewapp.glue.sign_forget_password.navigate.AdapterSignForgetPasswordRoute
import com.example.sign_forget_password.navigate.NavigateSignForgetPasswordRoute
import com.example.sing_in.navigate.NavigateSignInRoute
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