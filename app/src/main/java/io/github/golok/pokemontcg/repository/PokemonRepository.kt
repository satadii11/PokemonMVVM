package io.github.golok.pokemontcg.repository

import io.github.golok.pokemontcg.datastore.pokemon.PokemonDataStore
import io.github.golok.pokemontcg.model.PokemonCard

class PokemonRepository(
    private val pokemonLocalDataStore: PokemonDataStore,
    private val pokemonRemoteDataStore: PokemonDataStore
) {
    suspend fun getPokemons(set: String): MutableList<PokemonCard>? {
        val cache = pokemonLocalDataStore.getPokemons(set)
        if (cache != null) return cache
        val response = pokemonRemoteDataStore.getPokemons(set)
        pokemonLocalDataStore.addAll(set, response)
        return response
    }
}