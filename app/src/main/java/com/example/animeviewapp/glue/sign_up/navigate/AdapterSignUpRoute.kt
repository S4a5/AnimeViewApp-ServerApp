package com.example.animeviewapp.glue.sign_up.navigate

import android.util.Log
import androidx.navigation.NavController
import com.example.core.RouteScreen
import com.example.sign_up.navigate.NavigateSignUpRoute
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdapterSignUpRoute @Inject constructor() : NavigateSignUpRoute {

    private var navCon: NavController? = null
    fun setNavControl(navController: NavController) {
        navCon = navController
        Log.d("1231234","1111111111111")
    }
    override fun navToBack() {
        if (navCon != null) {
            navCon?.navigate(RouteScreen.SignIn.route)
        }
    }

    override fun navToSignIn() {
        if (navCon != null) {
            navCon?.navigate(RouteScreen.SignIn.route)
        }
    }

}