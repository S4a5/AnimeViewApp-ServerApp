package com.example.sign_forget_password.navigate

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.sign_forget_password.ScreenSignForgetPassword


fun NavGraphBuilder.routeScreenSingForgetPassword(route: String) {
    composable(route) {
        ScreenSignForgetPassword()
    }
}