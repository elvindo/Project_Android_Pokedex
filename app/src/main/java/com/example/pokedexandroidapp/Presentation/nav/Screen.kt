package com.example.pokedexandroidapp.Presentation.nav

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Detail : Screen("detail/{name}") {
        fun createRoute(name: String) = "detail/$name"
    }
    object Profile : Screen("profile")
}
