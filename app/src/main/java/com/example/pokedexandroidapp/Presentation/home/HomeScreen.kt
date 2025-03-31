package com.example.pokedexandroidapp.Presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokedexandroidapp.Presentation.nav.BottomBar
import com.example.pokedexandroidapp.Presentation.nav.Screen

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = remember { HomeViewModel() }
    val pokemonList by viewModel.pokemonList.collectAsState()
    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loadMorePokemon()
    }

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { padding ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
        ) {

            // Search Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = { Text("Cari Pokémon") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = {
                    if (searchText.isNotBlank()) {
                        navController.navigate(Screen.Detail.createRoute(searchText.lowercase()))
                    }
                }) {
                    Text("Cari")
                }
            }

            // List Pokémon
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(pokemonList) { pokemon ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                navController.navigate(Screen.Detail.createRoute(pokemon.name))
                            }
                    ) {
                        Text(
                            text = pokemon.name.replaceFirstChar { it.uppercase() },
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                item {
                    LaunchedEffect(Unit) {
                        viewModel.loadMorePokemon()
                    }
                }
            }
        }
    }
}
