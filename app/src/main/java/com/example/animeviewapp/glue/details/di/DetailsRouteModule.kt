package com.example.animeviewapp.glue.details.di

import com.example.animeviewapp.glue.details.navigation.AdapterDetailsRoute
import com.example.details.navigation.NavigationDetails
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface DetailsRouteModule {
    @Binds
    fun bindDetailsRouteModel(adapter:AdapterDetailsRoute):NavigationDetails

}