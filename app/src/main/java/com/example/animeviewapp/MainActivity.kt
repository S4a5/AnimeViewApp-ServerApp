package com.example.animeviewapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.animeviewapp.bottom_bar.BottomNavigation
import com.example.animeviewapp.glue.details.navigation.AdapterDetailsRoute
import com.example.animeviewapp.glue.home.navigate.AdapterHomeRoute
import com.example.animeviewapp.glue.sign_forget_password.navigate.AdapterSignForgetPasswordRoute
import com.example.animeviewapp.glue.sign_in.navigate.AdapterSignInRoute
import com.example.animeviewapp.glue.sign_up.navigate.AdapterSignUpRoute
import com.example.animeviewapp.model.RouteScreen
import com.example.core.ui.theme.AnimeViewAppTheme
import com.example.details.navigation.routeScreenDetails
import com.example.home.navigate.routeScreenHome
import com.example.sign_forget_password.navigate.routeScreenSingForgetPassword
import com.example.sign_up.navigate.routeScreenSingUp
import com.example.sing_in.navigate.routeScreenSingIn
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeViewAppTheme {
                val navController = rememberNavController()
                adapterSignInRoute.setNavControl(navController)
                adapterSignUpRoute.setNavControl(navController)
                adapterSignForgetPasswordRoute.setNavControl(navController)

                adapterHomeRoute.setNavControl(navController)
                adapterHomeRoute.setRouteDetails(RouteScreen.SignDetails.route)

                adapterDetailsRoute.setNavControl(navController)

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

                        routeScreenDetails(RouteScreen.SignDetails.route)


                    }
                }
            }
        }
    }
}


