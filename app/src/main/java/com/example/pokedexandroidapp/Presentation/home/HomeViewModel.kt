package com.example.pokedexandroidapp.Presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexandroidapp.data.remote.model.PokemonResult
import com.example.pokedexandroidapp.data.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = PokemonRepository()

    private val _pokemonList = MutableStateFlow<List<PokemonResult>>(emptyList())
    val pokemonList: StateFlow<List<PokemonResult>> = _pokemonList

    private var offset = 0
    private val pageSize = 10
    private var isLoading = false

    fun loadMorePokemon() {
        if (isLoading) return
        isLoading = true

        viewModelScope.launch {
            val newData = repository.getPokemonList(offset)
            _pokemonList.value = _pokemonList.value + newData
            offset += pageSize
            isLoading = false
        }
    }
}
