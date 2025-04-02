package com.example.pokedexandroidapp.Presentation.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val icon: ImageVector? = null
) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home", Icons.Default.Home)
    object Detail : Screen("detail/{name}") {
        fun createRoute(name: String) = "detail/$name"
    }
    object Profile : Screen("profile", Icons.Default.Person)
}
