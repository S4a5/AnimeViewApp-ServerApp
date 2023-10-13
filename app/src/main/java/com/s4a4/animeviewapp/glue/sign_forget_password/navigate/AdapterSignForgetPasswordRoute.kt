package com.s4a4.animeviewapp.glue.sign_forget_password.navigate

import android.util.Log
import androidx.navigation.NavController
import com.s4a4.sign_forget_password.navigate.NavigateSignForgetPasswordRoute
import com.s4a4.sing_in.navigate.NavigateSignInRoute
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class AdapterSignForgetPasswordRoute @Inject constructor(): NavigateSignForgetPasswordRoute {
    private var navCon: NavController? = null
    fun setNavControl(navController: NavController) {
        if (navCon != null) {
            navCon = navController
        }
    }

    override fun navToSignIn() {

    }
}