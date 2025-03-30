package com.example.pokedexandroidapp.Presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokedexandroidapp.Presentation.nav.BottomBar

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) {
        Column(modifier = Modifier.padding(it).padding(16.dp)) {
            Text("Home Screen")
        }
    }
}
