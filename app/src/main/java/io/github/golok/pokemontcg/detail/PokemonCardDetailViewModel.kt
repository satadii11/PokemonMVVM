package io.github.golok.pokemontcg.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.golok.pokemontcg.model.PokemonCard

class PokemonCardDetailViewModel : ViewModel() {
    val viewState = MutableLiveData<PokemonCardDetailViewState>()

    init {
        viewState.value = PokemonCardDetailViewState(null)
    }

    fun setData(pokemonCard: PokemonCard) {
        viewState.value = viewState.value?.copy(data = pokemonCard)
    }
}