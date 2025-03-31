package com.example.pokedexandroidapp.data.repository

import com.example.pokedexandroidapp.data.remote.api.RetrofitInstance
import com.example.pokedexandroidapp.data.remote.model.PokemonResult

class PokemonRepository {
    suspend fun getPokemonList(offset: Int = 0): List<PokemonResult> {
        return RetrofitInstance.api.getPokemonList(limit = 10, offset = offset).results
    }
}
