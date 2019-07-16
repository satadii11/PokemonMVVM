package io.github.golok.pokemontcg.pokemonlist

import io.github.golok.pokemontcg.model.PokemonCard
import java.lang.Exception

data class PokemonListViewState (
    val loading: Boolean = false,
    val error: Exception? = null,
    val data: MutableList<PokemonCard>? = null
)