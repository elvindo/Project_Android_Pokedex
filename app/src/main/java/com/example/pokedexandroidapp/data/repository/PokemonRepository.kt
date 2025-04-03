package com.example.pokedexandroidapp.data.repository

import com.example.pokedexandroidapp.data.remote.api.RetrofitInstance
import com.example.pokedexandroidapp.data.remote.model.PokemonDetailResponse
import com.example.pokedexandroidapp.data.remote.model.PokemonResult

class PokemonRepository {
    suspend fun getPokemonList(offset: Int = 0, limit: Int = 10): List<PokemonResult> {
        val response = RetrofitInstance.api.getPokemonList(limit = limit, offset = offset)
        return response.results.mapIndexed { index, result ->

            val id = offset + index + 1
            if (result.url.isBlank()) {
                result.copy(url = "https://pokeapi.co/api/v2/pokemon/$id/")
            } else result
        }
    }

    suspend fun getPokemonDetail(name: String): PokemonDetailResponse {
        return RetrofitInstance.api.getPokemonDetail(name)
    }
}

