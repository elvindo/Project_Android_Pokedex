package com.example.pokedexandroidapp.data.remote.model

data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String,
    val id: Int = 0,
    val imageUrl: String = ""
)
