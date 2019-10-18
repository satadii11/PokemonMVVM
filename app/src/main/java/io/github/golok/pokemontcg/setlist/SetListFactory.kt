package io.github.golok.pokemontcg.setlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.golok.pokemontcg.repository.PokemonSetRepository

class SetListFactory(
    private val setRepository: PokemonSetRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SetListViewModel::class.java))
            return SetListViewModel(setRepository) as T
        throw IllegalArgumentException()
    }
}