package com.example.pokedexandroidapp.Presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Text(
                    text = "Pokemon Detail",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Gambar Pokemon
                pokemon?.let { current ->
                    val imageUrl =
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${current.id}.png"

                    AsyncImage(
                        model = imageUrl,
                        contentDescription = current.name,
                        modifier = Modifier
                            .size(160.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Nama Pokemon
                OutlinedTextField(
                    value = pokemon?.name?.replaceFirstChar { it.uppercase() } ?: "-",
                    onValueChange = {},
                    enabled = true,
                    textStyle = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = Color.Black,
                        disabledBorderColor = Color.LightGray,
                        disabledContainerColor = Color(0xFFF8F8F8)
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Abilities:",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Abilities
                pokemon?.abilities?.forEach { ability ->
                    OutlinedButton(
                        onClick = {},
                        enabled = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color(0xFFF8F8F8),
                            contentColor = Color.Black,
                            disabledContentColor = Color.Black
                        )
                    ) {
                        Text(
                            text = ability.ability.name.replaceFirstChar { it.uppercase() },
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
