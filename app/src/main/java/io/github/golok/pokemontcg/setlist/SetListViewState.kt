package io.github.golok.pokemontcg.setlist

import io.github.golok.pokemontcg.model.PokemonSet

data class SetListViewState (
    var loading: Boolean = false,
    var error: Exception? = null,
    var data: MutableList<PokemonSet>? = null
)