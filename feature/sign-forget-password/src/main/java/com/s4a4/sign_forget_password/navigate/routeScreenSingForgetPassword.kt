package com.s4a4.sign_forget_password.navigate

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.s4a4.sign_forget_password.ScreenSignForgetPassword


fun NavGraphBuilder.routeScreenSingForgetPassword(route: String) {
    composable(route) {
        ScreenSignForgetPassword()
    }
}