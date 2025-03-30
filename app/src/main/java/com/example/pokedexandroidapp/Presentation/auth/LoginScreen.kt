package com.example.pokedexandroidapp.Presentation.auth

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokedexandroidapp.Presentation.nav.Screen

@Composable
fun LoginScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center) {
        Text("Login Screen")
        Button(onClick = { navController.navigate(Screen.Home.route) }) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate(Screen.Register.route) }) {
            Text("Don't have an account? Register")
        }
    }
}
