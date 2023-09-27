package com.example.animeviewapp.glue.home.navigate

import android.util.Log
import androidx.navigation.NavController
import com.example.home.navigate.NavigateHome
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class AdapterHomeRoute @Inject constructor(): NavigateHome {
    private var navCon: NavController? = null
    fun setNavControl(navController: NavController) {
        navCon = navController
        Log.d("1231234","1111111111111")
    }
}