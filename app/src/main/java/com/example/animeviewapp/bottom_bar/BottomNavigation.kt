package com.example.animeviewapp.bottom_bar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.animeviewapp.model.RouteScreen

@Composable
fun BottomNavigation(navController: NavController, list: List<RouteScreen>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
//    if (list.find { it.route == currentRoute } != null) {
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.onSecondary) {
            list.forEach {
                if (it.icon != null) {
                    NavigationBarItem(selected = true,
                    onClick = {

                    },
                    icon = { painterResource(id = it.icon) })

                }

            }
        }
//    }
}
@Preview
@Composable
fun Preview() {
    val navController = rememberNavController()
    BottomNavigation(
        navController = navController,
        list = listOf(RouteScreen.SignHome,RouteScreen.SignHome,RouteScreen.SignHome)
    )
}