package com.example.pokedexandroidapp.data.remote.model

data class PokemonDetailResponse(
    val name: String,
    val abilities: List<AbilityEntry>
)

data class AbilityEntry(
    val ability: Ability
)

data class Ability(
    val name: String
)
