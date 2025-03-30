package com.example.pokedexandroidapp.Presentation.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.Composable

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(Screen.Home, Screen.Profile)
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(Screen.Home.route)
                        launchSingleTop = true
                    }
                },
                icon = { Icon(Icons.Default.Home, contentDescription = screen.route) },
                label = { Text(screen.route.replaceFirstChar { it.uppercase() }) }
            )
        }
    }
}




