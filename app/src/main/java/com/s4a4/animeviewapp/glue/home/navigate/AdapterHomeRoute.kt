package com.s4a4.animeviewapp.glue.home.navigate

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import androidx.navigation.navArgument
import com.s4a4.home.navigate.NavigateHome
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class AdapterHomeRoute @Inject constructor(): NavigateHome {
    private var navCon: NavController? = null
    private var routeDetails: String? = null
    fun setNavControl(navController: NavController) {
        navCon = navController

    }
    fun setRouteDetails(routeDetails: String){
        this.routeDetails = routeDetails
    }

    override fun navigateToDetails(animeId: Int) {
        if (routeDetails == null) throw Throwable("route details = null")
        navCon?.navigate("$routeDetails/$animeId")
    }
}