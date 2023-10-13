package com.s4a4.details.navigation

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.s4a4.details.ScreenDetails

fun NavGraphBuilder.routeScreenDetails(route: String) {
    composable("$route/{animeId}",listOf(navArgument("animeId") { type = NavType.IntType })) { entry ->
        val id = entry.arguments?.getInt("animeId")
        id?.let {
            ScreenDetails(it)
        }?: kotlin.run {
            Toast.makeText(LocalContext.current,"null animeId",Toast.LENGTH_LONG).show()
        }
    }
}