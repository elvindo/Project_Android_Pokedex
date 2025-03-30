package com.example.pokedexandroidapp.Presentation.detail

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
fun DetailScreen(navController: NavController, name: String) {
    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) {
        Column(modifier = Modifier.padding(it).padding(16.dp)) {
            Text("Detail of $name")
        }
    }
}
