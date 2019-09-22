package io.github.golok.pokemontcg.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.golok.pokemontcg.model.PokemonCard

class PokemonCardDetailViewModel : ViewModel() {
    private val mViewState = MutableLiveData<PokemonCardDetailViewState>().apply {
        value = PokemonCardDetailViewState(null)
    }
    val viewState: LiveData<PokemonCardDetailViewState>
        get() = mViewState

    fun setData(pokemonCard: PokemonCard) {
        mViewState.value = mViewState.value?.copy(data = pokemonCard)
    }
}