package com.example.animeviewapp.glue.details.navigation

import android.telecom.Call.Details
import android.util.Log
import androidx.navigation.NavController
import com.example.details.navigation.NavigationDetails
import javax.inject.Inject

class AdapterDetailsRoute @Inject constructor():NavigationDetails {

    private var navCon: NavController? = null
    fun setNavControl(navController: NavController) {
        navCon = navController
    }
    override fun backScreen() {
        navCon?.popBackStack()
    }
}