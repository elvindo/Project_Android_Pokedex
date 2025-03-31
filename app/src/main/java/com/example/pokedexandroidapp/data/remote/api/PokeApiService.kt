package com.example.pokedexandroidapp.data.remote.api

import com.example.pokedexandroidapp.data.remote.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0
    ): PokemonResponse
}

