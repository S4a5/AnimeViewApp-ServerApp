package com.example.animeviewapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core.ui.theme.AnimeViewAppTheme
import com.example.sign_up.ScreenSignUp
import com.example.sing_in.ScreenSignIn


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeViewAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = RouteScreen.SignIn.route
                ) {
                    composable(RouteScreen.SignIn.route) {
                        ScreenSignIn(onClickRegistry = {
                            navController.navigate(RouteScreen.SignUp.route)
                        }, onClickForgetPassword = {
                            navController.navigate(RouteScreen.SignForgetPassword.route)
                        }, onClickSingIn = {

                        })

                    }
                    composable(RouteScreen.SignUp.route) {
                        ScreenSignUp(onClickBack = {
                            navController.navigate(RouteScreen.SignIn.route)
                        }, onClickRegistry = {

                        })
                    }
                    composable(RouteScreen.SignForgetPassword.route) {

                    }
                }
            }
        }
    }
}

sealed class RouteScreen(val route: String) {
    object SignIn : RouteScreen("sign_in")
    object SignUp : RouteScreen("sign_up")
    object SignForgetPassword : RouteScreen("sign_forget_password")
}
