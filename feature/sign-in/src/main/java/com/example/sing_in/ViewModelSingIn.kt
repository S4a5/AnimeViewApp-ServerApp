package com.example.sing_in

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.sing_in.navigate.NavigateSignInRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelSingIn @Inject constructor(private val navigateSignInRoute: NavigateSignInRoute) :ViewModel() {
    fun onToRegister(){
        Log.d("1231233","111111")
        navigateSignInRoute.navToRegister()
    }
    fun onToForgetPassword(){
        navigateSignInRoute.navToForgetPassword()
    }
    fun onToHome(){
        navigateSignInRoute.navToHome()
    }
}