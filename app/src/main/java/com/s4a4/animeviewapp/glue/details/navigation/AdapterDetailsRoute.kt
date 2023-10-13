package com.s4a4.animeviewapp.glue.details.navigation

import android.util.Log
import androidx.navigation.NavController
import com.s4a4.details.navigation.NavigationDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdapterDetailsRoute @Inject constructor():NavigationDetails {

    private var navCon: NavController? = null
    private var routeVideoPlayer: String? = null
    fun setNavControl(navController: NavController) {
        navCon = navController
    }

    fun setRouteVideoPlayer(routeVideoPlayer: String){
        this.routeVideoPlayer = routeVideoPlayer
    }

    override fun backScreen() {
        navCon?.popBackStack()
    }

    override fun openVideoPlayer(animeId: Int, voice: String, episodeIndexInList: Int) {
        if (routeVideoPlayer == null) throw Throwable("route details = null")
        navCon?.navigate("$routeVideoPlayer/$animeId/$voice/$episodeIndexInList")
    }
}