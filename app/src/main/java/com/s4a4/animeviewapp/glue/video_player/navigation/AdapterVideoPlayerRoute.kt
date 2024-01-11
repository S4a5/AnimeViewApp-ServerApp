package com.s4a4.animeviewapp.glue.video_player.navigation

import android.telecom.Call.Details
import android.util.Log
import androidx.navigation.NavController
import com.s4a4.details.navigation.NavigationDetails
import com.s4a4.player.navigate.NavigateVideoPlayer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdapterVideoPlayerRoute @Inject constructor():NavigateVideoPlayer {

    private var navCon: NavController? = null
    fun setNavControl(navController: NavController) {
        navCon = navController

    }

    override fun screenGetOut() {
        println("qqq")
        println(navCon == null)
        navCon?.popBackStack()
    }
}