package com.example.animeviewapp.bottom_bar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.secondary
    ) {
        list.forEach {
            if (it.icon != null) {
                NavigationBarItem(selected = currentRoute == it.route,
                    onClick = {

                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = it.icon),
                            contentDescription = "",
                            tint = if (currentRoute == it.route) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSecondary
                            }
                        )
                    })
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    val navController = rememberNavController()
    BottomNavigation(
        navController = navController,
        list = listOf(RouteScreen.SignHome, RouteScreen.SignHome, RouteScreen.SignHome)
    )
}