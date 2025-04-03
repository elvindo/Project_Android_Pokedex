package com.example.pokedexandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.pokedexandroidapp.Presentation.nav.AppNavGraph
import com.example.pokedexandroidapp.data.local.SessionManager
import com.example.pokedexandroidapp.ui.theme.PokedexAndroidAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokedexAndroidAppTheme {
                val navController = rememberNavController()
                val session = remember { SessionManager(this) }
                val email = session.getEmail()

                LaunchedEffect(Unit) {
                    if (email == null) {
                        navController.navigate("login") {
                            popUpTo(0)
                        }
                    }
                }

                AppNavGraph(navController = navController)
            }
        }
    }
}

