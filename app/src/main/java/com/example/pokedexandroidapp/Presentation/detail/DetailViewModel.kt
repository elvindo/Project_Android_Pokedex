package com.example.pokedexandroidapp.Presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexandroidapp.data.remote.model.PokemonDetailResponse
import com.example.pokedexandroidapp.data.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val repository = PokemonRepository()

    private val _pokemonDetail = MutableStateFlow<PokemonDetailResponse?>(null)
    val pokemonDetail: StateFlow<PokemonDetailResponse?> = _pokemonDetail

    fun getDetail(name: String) {
        viewModelScope.launch {
            try {
                val response = repository.getPokemonDetail(name)
                _pokemonDetail.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
