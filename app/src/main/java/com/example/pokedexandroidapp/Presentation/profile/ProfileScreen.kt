package com.example.pokedexandroidapp.Presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokedexandroidapp.Presentation.nav.BottomBar
import com.example.pokedexandroidapp.Presentation.nav.Screen
import com.example.pokedexandroidapp.data.local.SessionManager

@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val session = remember { SessionManager(context) }

    val name = remember { session.getName() ?: "-" }
    val email = remember { session.getEmail() ?: "-" }

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Profil Pengguna", style = MaterialTheme.typography.titleLarge)
            Text("Nama: $name")
            Text("Email: $email")

            Button(onClick = {
                session.clear()
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            }) {
                Text("Logout")
            }
        }
    }
}