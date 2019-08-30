package io.github.golok.pokemontcg.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.golok.pokemontcg.repository.PokemonSetRepository

class MainViewModelFactory(
    private val setRepository: PokemonSetRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(setRepository) as T
        throw IllegalArgumentException()
    }
}