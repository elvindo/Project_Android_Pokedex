package com.example.pokedexandroidapp.Presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexandroidapp.data.remote.model.PokemonResult
import com.example.pokedexandroidapp.data.repository.PokemonRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = PokemonRepository()

    private val _pokemonList = MutableStateFlow<List<PokemonResult>>(emptyList())
    val pokemonList: StateFlow<List<PokemonResult>> = _pokemonList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _allPokemon = MutableStateFlow<List<PokemonResult>>(emptyList())
    val allPokemon: StateFlow<List<PokemonResult>> = _allPokemon

    private var offset = 0
    private val limit = 10

    fun loadMorePokemon() {
        if (_isLoading.value) return

        _isLoading.value = true
        viewModelScope.launch {
            try {
                delay(1000)
                val newData = repository.getPokemonList(offset = offset, limit = limit)
                _pokemonList.value = _pokemonList.value + newData.map { pokemon ->

                    val id = pokemon.url.split("/").dropLast(1).lastOrNull()?.toIntOrNull() ?: 0
                    pokemon.copy(id = id, imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png")
                }
                offset += limit
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }


    fun fetchAllPokemonForSearch() {
        viewModelScope.launch {
            try {
                val fullList = repository.getPokemonList(limit = 1000, offset = 0)
                _allPokemon.value = fullList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}  