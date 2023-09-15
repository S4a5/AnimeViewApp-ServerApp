package com.example.sing_in.navigate

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.sing_in.ScreenSignIn

fun NavGraphBuilder.routeScreenSingIn(route: String) {
    composable(route) {
        ScreenSignIn()
    }
}