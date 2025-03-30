package com.example.pokedexandroidapp.Presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pokedexandroidapp.Presentation.auth.LoginScreen
import com.example.pokedexandroidapp.Presentation.auth.RegisterScreen
import com.example.pokedexandroidapp.Presentation.detail.DetailScreen
import com.example.pokedexandroidapp.Presentation.home.HomeScreen
import com.example.pokedexandroidapp.Presentation.profile.ProfileScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Register.route) { RegisterScreen(navController) }
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Detail.route) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            DetailScreen(navController, name)
        }
        composable(Screen.Profile.route) { ProfileScreen(navController) }
    }
}
