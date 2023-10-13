package com.s4a4.sign_up.navigate

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelSingUp @Inject constructor(private val route:NavigateSignUpRoute):ViewModel() {
    fun onNavBack(){
        route.navToBack()
    }
}