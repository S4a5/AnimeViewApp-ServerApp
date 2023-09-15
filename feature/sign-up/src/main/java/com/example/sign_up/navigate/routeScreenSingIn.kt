package com.example.sign_up.navigate

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.sign_up.ScreenSignUp

fun NavGraphBuilder.routeScreenSingUp(route: String) {
    composable(route) {
        ScreenSignUp()
    }
}