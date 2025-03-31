package com.example.pokedexandroidapp.Presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokedexandroidapp.Presentation.nav.BottomBar

@Composable
fun DetailScreen(navController: NavController, name: String) {
    val viewModel = remember { DetailViewModel() }
    val pokemon by viewModel.pokemonDetail.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getDetail(name)
    }

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(16.dp)
        ) {
            Text(text = "Nama: ${pokemon?.name?.replaceFirstChar { it.uppercase() } ?: "-"}")
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Abilities:")
            pokemon?.abilities?.forEach {
                Text("- ${it.ability.name}")
            }
        }
    }
}


