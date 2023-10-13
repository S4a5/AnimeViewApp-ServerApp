package com.s4a4.sing_in.navigate

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.s4a4.sing_in.ScreenSignIn

fun NavGraphBuilder.routeScreenSingIn(route: String) {
    composable(route) {
        ScreenSignIn()
    }
}