package com.example.animeviewapp.model

import androidx.annotation.DrawableRes
import com.example.animeviewapp.R


sealed class RouteScreen(val route: String,@DrawableRes val icon:Int?=null,val title:String? =null) {
    object SignIn : RouteScreen("sign_in")
    object SignUp : RouteScreen("sign_up")
    object SignForgetPassword : RouteScreen("sign_forget_password")
    object SignHome : RouteScreen("home",icon = R.drawable.ic_home,title = "Главная")
    object Details : RouteScreen("details")
}
