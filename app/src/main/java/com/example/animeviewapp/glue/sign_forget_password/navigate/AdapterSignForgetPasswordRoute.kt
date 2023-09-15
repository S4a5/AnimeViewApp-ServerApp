package com.example.animeviewapp.glue.sign_forget_password.navigate

import android.util.Log
import androidx.navigation.NavController
import com.example.sign_forget_password.navigate.NavigateSignForgetPasswordRoute
import com.example.sing_in.navigate.NavigateSignInRoute
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