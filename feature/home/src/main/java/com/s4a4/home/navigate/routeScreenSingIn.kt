package com.s4a4.home.navigate

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.s4a4.home.ScreenHome

fun NavGraphBuilder.routeScreenHome(route: String) {
    composable(route) {
        ScreenHome()
    }
}