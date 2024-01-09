package com.s4a4.animeviewapp

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.s4a4.animeviewapp.bottom_bar.BottomNavigation
import com.s4a4.animeviewapp.glue.details.navigation.AdapterDetailsRoute
import com.s4a4.animeviewapp.glue.home.navigate.AdapterHomeRoute
import com.s4a4.animeviewapp.glue.sign_forget_password.navigate.AdapterSignForgetPasswordRoute
import com.s4a4.animeviewapp.glue.sign_in.navigate.AdapterSignInRoute
import com.s4a4.animeviewapp.glue.sign_up.navigate.AdapterSignUpRoute
import com.s4a4.animeviewapp.glue.video_player.navigation.AdapterVideoPlayerRoute
import com.s4a4.animeviewapp.model.RouteScreen
import com.s4a4.core.ui.theme.AnimeViewAppTheme
import com.s4a4.details.navigation.routeScreenDetails
import com.s4a4.home.navigate.routeScreenHome
import com.s4a4.player.navigate.routeScreenVideoPlayer
import com.s4a4.sign_forget_password.navigate.routeScreenSingForgetPassword
import com.s4a4.sign_up.navigate.routeScreenSingUp
import com.s4a4.sing_in.navigate.routeScreenSingIn
import dagger.hilt.android.AndroidEntryPoint
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
                        AvtoOrnamentation(navController)
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
@Composable
fun AvtoOrnamentation(navController:NavController){
    val currentScreen by navController.currentBackStackEntryAsState()
    val currentRoute = currentScreen?.destination?.route
    val activity = LocalContext.current as Activity
    if (currentRoute == RouteScreen.RouteVideoPlayer.route){
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }else{
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}


