package io.github.golok.pokemontcg.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.golok.pokemontcg.repository.PokemonRepository

class MainViewModelFactory(
    private val pokemonRepository: PokemonRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(pokemonRepository) as T
        throw IllegalArgumentException()
    }
}