package io.github.golok.pokemontcg.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.golok.pokemontcg.repository.PokemonRepository

class PokemonListViewModelFactory(
        private val pokemonRepository: PokemonRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonListViewModel::class.java))
            return PokemonListViewModel(pokemonRepository) as T
        throw IllegalArgumentException()
    }
}