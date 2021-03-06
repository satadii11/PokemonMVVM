package io.github.golok.pokemontcg.datastore.pokemon

import io.github.golok.pokemontcg.model.PokemonCard

interface PokemonCardDataStore {
    suspend fun getPokemons(set: String): MutableList<PokemonCard>?
    suspend fun addAll(set: String, pokemons: MutableList<PokemonCard>?)
}