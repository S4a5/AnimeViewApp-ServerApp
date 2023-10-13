package com.s4a4.animeviewapp.glue.sign_in.navigate

import android.util.Log
import androidx.navigation.NavController
import com.s4a4.animeviewapp.model.RouteScreen
import com.s4a4.sing_in.navigate.NavigateSignInRoute
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class AdapterSignInRoute @Inject constructor(): NavigateSignInRoute {
    private var navCon: NavController? = null
    fun setNavControl(navController: NavController) {
        navCon = navController
        Log.d("1231234","1111111111111")
    }

    override fun navToRegister() {
        if (navCon != null) {
            navCon?.navigate(RouteScreen.SignUp.route)
        }
    }

    override fun navToForgetPassword() {
        if (navCon != null) {
            navCon?.navigate(RouteScreen.SignForgetPassword.route)
        }
    }

    override fun navToHome() {
        if (navCon != null) {
            navCon?.navigate(RouteScreen.SignHome.route)
        }
    }

    override fun navToGoogle() {

    }

    override fun navToGuest() {

    }
}