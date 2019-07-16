package io.github.golok.pokemontcg.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.golok.pokemontcg.repository.PokemonRepository
import io.github.golok.pokemontcg.repository.SetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val pokemonSets: SetRepository
) : ViewModel() {
    val viewState = MutableLiveData<MainViewState>().apply {
        value = MainViewState(loading = true)
    }

    fun getSets() = GlobalScope.launch(Dispatchers.Main) {
        try {
            val data = pokemonSets.getSets()
            viewState.value = viewState.value?.copy(loading = false, error = null, data = data)
        } catch (ex: Exception) {
            viewState.value = viewState.value?.copy(loading = false, error = ex, data = null)
        }
    }
}
