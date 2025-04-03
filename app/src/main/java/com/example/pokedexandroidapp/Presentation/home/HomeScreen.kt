package com.example.pokedexandroidapp.Presentation.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokedexandroidapp.Presentation.nav.BottomBar
import com.example.pokedexandroidapp.Presentation.nav.Screen
import com.example.pokedexandroidapp.data.remote.model.PokemonResult
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = remember { HomeViewModel() }
    val pokemonList by viewModel.pokemonList.collectAsState()
    val allPokemon by viewModel.allPokemon.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    var searchText by remember { mutableStateOf("") }

    // Initial Load
    LaunchedEffect(Unit) {
        viewModel.loadMorePokemon()
        viewModel.fetchAllPokemonForSearch()
    }

    // Infinite scroll
    LaunchedEffect(pokemonList, listState, searchText) {
        if (searchText.isBlank()) {
            snapshotFlow { listState.layoutInfo.visibleItemsInfo }
                .collect { visibleItems ->
                    val lastVisibleItem = visibleItems.lastOrNull()
                    if (lastVisibleItem?.index == pokemonList.lastIndex) {
                        viewModel.loadMorePokemon()
                    }
                }
        }
    }

    val filteredList = if (searchText.isNotBlank()) {
        allPokemon.filter { it.name.startsWith(searchText.trim(), ignoreCase = true) }
    } else pokemonList

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = { BottomBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "POKEDEX",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = { Text("Search a pokemon") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (searchText.isNotBlank()) {
                            val matched = allPokemon.any {
                                it.name.equals(searchText.trim(), ignoreCase = true)
                            }
                            if (matched) {
                                navController.navigate(Screen.Detail.createRoute(searchText.lowercase()))
                            } else {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        "\"$searchText\" is not found"
                                    )
                                }
                            }
                        }
                    },
                    modifier = Modifier.height(56.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Cari", fontWeight = FontWeight.Medium)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (filteredList.isEmpty() && searchText.isNotBlank()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 80.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        text = "\"$searchText\" is not recognized",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.outline,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            } else {
                LazyColumn(state = listState) {
                    items(filteredList) { pokemon ->
                        PokemonCard(pokemon = pokemon) {
                            navController.navigate(Screen.Detail.createRoute(pokemon.name))
                        }
                    }

                    if (isLoading && searchText.isBlank()) {
                        item {
                            Spacer(modifier = Modifier.height(12.dp))
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonCard(
    pokemon: PokemonResult,
    onClick: () -> Unit
) {
    val number = pokemon.url.trimEnd('/').split("/").lastOrNull()?.toIntOrNull()
    val imageUrl = number?.let {
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$it.png"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(
                1.dp,
                MaterialTheme.colorScheme.outline,
                RoundedCornerShape(8.dp)
            )
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier
                    .size(60.dp)
                    .padding(end = 12.dp),
                contentScale = ContentScale.Fit
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = pokemon.name.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Text(
                text = "#${number ?: "N/A"}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.outline
                )
            )
        }
    }
}
