package io.github.golok.pokemontcg.pokemonlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.golok.pokemontcg.repository.PokemonCardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val pokemonRepository: PokemonCardRepository
) : ViewModel() {
    val viewState = MutableLiveData<PokemonListViewState>().apply {
        value = PokemonListViewState(loading = true)
    }

    fun getPokemons(set: String) = GlobalScope.launch(Dispatchers.Main) {
        try {
            val data = pokemonRepository.getPokemons(set)
            viewState.value = viewState.value?.copy(loading = false, error = null, data = data)
        } catch (ex: Exception) {
            viewState.value = viewState.value?.copy(loading = false, error = ex, data = null)
        }
    }
}