package io.github.golok.pokemontcg.main

import io.github.golok.pokemontcg.model.PokemonSet

data class MainViewState (
    var loading: Boolean = false,
    var error: Exception? = null,
    var data: MutableList<PokemonSet>? = null
)