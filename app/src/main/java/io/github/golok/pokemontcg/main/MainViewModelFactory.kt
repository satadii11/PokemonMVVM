package io.github.golok.pokemontcg.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.golok.pokemontcg.model.PokemonSet
import io.github.golok.pokemontcg.repository.PokemonRepository
import io.github.golok.pokemontcg.repository.SetRepository

class MainViewModelFactory(
    private val setRepository: SetRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(setRepository) as T
        throw IllegalArgumentException()
    }
}