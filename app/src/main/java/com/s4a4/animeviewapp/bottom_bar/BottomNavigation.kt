package com.s4a4.animeviewapp.bottom_bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.s4a4.animeviewapp.model.RouteScreen

@Composable
fun BottomNavigation(navController: NavController, list: List<RouteScreen>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    AnimatedVisibility(visible = list.find { it.route == currentRoute } != null,
        enter = expandVertically()+ fadeIn(),
        exit = shrinkVertically()+ fadeOut(),)  {
        val color = MaterialTheme.colorScheme.secondaryContainer
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    val strokeWidth = 10f
                    val x = size.width - strokeWidth

                    //top line
                    drawLine(
                        color = color,
                        start = Offset(0f, 0f), //(0,0) at top-left point of the box
                        end = Offset(x, 0f), //top-right point of the box
                        strokeWidth = strokeWidth
                    )
                },
            containerColor = MaterialTheme.colorScheme.onSurface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ) {
            list.forEach {
                if (it.icon != null && it.title != null) {
                    NavigationBarItem(
                        selected = currentRoute == it.route,
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
                        },
                        label = {
                                Text(text = it.title,color = if (currentRoute == it.route) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.onSecondary
                                }, style = MaterialTheme.typography.titleSmall)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onSurface,
                            indicatorColor = MaterialTheme.colorScheme.onSurface
                        )
                    )
                }
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