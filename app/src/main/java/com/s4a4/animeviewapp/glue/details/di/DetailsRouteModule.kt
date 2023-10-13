package com.s4a4.animeviewapp.glue.details.di

import com.s4a4.animeviewapp.glue.details.navigation.AdapterDetailsRoute
import com.s4a4.details.navigation.NavigationDetails
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