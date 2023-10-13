package com.s4a4.player.navigate

import ScreenVideoPlayer
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import okhttp3.Route

const val ANIME_ID = "animeId"
const val VOICE = "voice"
const val EPISODE_INDEX_IN_LIST = "episodeIndexInList"

fun NavGraphBuilder.routeScreenVideoPlayer(route: String) {
    composable(

        "$route/{$ANIME_ID}/{$VOICE}/{$EPISODE_INDEX_IN_LIST}",
        listOf(navArgument(ANIME_ID) { type = NavType.IntType },
            navArgument(VOICE) { type = NavType.StringType },
            navArgument(EPISODE_INDEX_IN_LIST) { type = NavType.IntType }
        )
    ) { entry ->
        val id = entry.arguments?.getInt(ANIME_ID)
        val voice = entry.arguments?.getString(VOICE)
        val episode = entry.arguments?.getInt(EPISODE_INDEX_IN_LIST)

        if (id != null && voice != null && episode != null) {

            ScreenVideoPlayer(
                id,
                voice,
                episode,
            )
        } else {
            Toast.makeText(LocalContext.current, "null argument", Toast.LENGTH_LONG).show()
        }
    }
}