package com.example.core

sealed class RouteScreen(val route: String) {
    object SignIn : RouteScreen("sign_in")
    object SignUp : RouteScreen("sign_up")
    object SignForgetPassword : RouteScreen("sign_forget_password")
    object SignHome : RouteScreen("home")
}
