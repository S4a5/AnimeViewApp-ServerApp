package com.example.animeviewapp.glue.video_player.navigation

import android.telecom.Call.Details
import android.util.Log
import androidx.navigation.NavController
import com.example.details.navigation.NavigationDetails
import com.example.player.navigate.NavigateVideoPlayer
import javax.inject.Inject

class AdapterVideoPlayerRoute @Inject constructor():NavigateVideoPlayer {

    private var navCon: NavController? = null
    fun setNavControl(navController: NavController) {
        navCon = navController
    }

    override fun screenGetOut() {
        navCon?.popBackStack()
    }
}