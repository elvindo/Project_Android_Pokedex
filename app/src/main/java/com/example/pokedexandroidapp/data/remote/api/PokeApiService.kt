package com.example.pokedexandroidapp.data.remote.api

import com.example.pokedexandroidapp.data.remote.model.PokemonDetailResponse
import com.example.pokedexandroidapp.data.remote.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0
    ): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): PokemonDetailResponse
}

