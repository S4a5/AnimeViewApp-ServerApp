package com.example.animeviewapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animeviewapp.bottom_bar.BottomNavigation
import com.example.animeviewapp.glue.details.navigation.AdapterDetailsRoute
import com.example.animeviewapp.glue.home.navigate.AdapterHomeRoute
import com.example.animeviewapp.glue.sign_forget_password.navigate.AdapterSignForgetPasswordRoute
import com.example.animeviewapp.glue.sign_in.navigate.AdapterSignInRoute
import com.example.animeviewapp.glue.sign_up.navigate.AdapterSignUpRoute
import com.example.animeviewapp.glue.video_player.navigation.AdapterVideoPlayerRoute
import com.example.animeviewapp.model.RouteScreen
import com.example.core.ui.theme.AnimeViewAppTheme
import com.example.details.navigation.routeScreenDetails
import com.example.home.navigate.routeScreenHome
import com.example.player.navigate.routeScreenVideoPlayer
import com.example.sign_forget_password.navigate.routeScreenSingForgetPassword
import com.example.sign_up.navigate.routeScreenSingUp
import com.example.sing_in.navigate.routeScreenSingIn
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.wait
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : ComponentActivity() {

    @Inject
    lateinit var adapterSignInRoute: AdapterSignInRoute

    @Inject
    lateinit var adapterSignUpRoute: AdapterSignUpRoute

    @Inject
    lateinit var adapterSignForgetPasswordRoute: AdapterSignForgetPasswordRoute

    @Inject
    lateinit var adapterHomeRoute: AdapterHomeRoute

    @Inject
    lateinit var adapterDetailsRoute: AdapterDetailsRoute

    @Inject
    lateinit var adapterVideoPlayerRoute: AdapterVideoPlayerRoute
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeViewAppTheme {
                val navController = rememberNavController()
                adapterSignInRoute.setNavControl(navController)
                adapterSignUpRoute.setNavControl(navController)
                adapterSignForgetPasswordRoute.setNavControl(navController)

                adapterHomeRoute.setNavControl(navController)
                adapterHomeRoute.setRouteDetails(RouteScreen.Details.route)


                adapterDetailsRoute.setNavControl(navController)
                adapterDetailsRoute.setRouteVideoPlayer(RouteScreen.RouteVideoPlayer.route)

                adapterVideoPlayerRoute.setNavControl(navController)

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {

                        BottomNavigation(
                            navController = navController,
                            list = listOf(RouteScreen.SignHome)
                        )

                    }
                ) {
                    NavHost(
                        modifier = Modifier.padding(it),
                        navController = navController,
                        startDestination = RouteScreen.SignHome.route,

                        ) {
                        routeScreenSingIn(RouteScreen.SignIn.route)

                        routeScreenSingUp(RouteScreen.SignUp.route)

                        routeScreenSingForgetPassword(RouteScreen.SignForgetPassword.route)

                        routeScreenHome(RouteScreen.SignHome.route)

                        routeScreenDetails(RouteScreen.Details.route)

                        routeScreenVideoPlayer(RouteScreen.RouteVideoPlayer.route)


                    }
                }
            }
        }
    }
}


