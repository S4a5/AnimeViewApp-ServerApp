package com.example.home.navigate

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.home.ScreenHome

fun NavGraphBuilder.routeScreenHome(route: String) {
    composable(route) {
        ScreenHome()
    }
}