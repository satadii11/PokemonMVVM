package io.github.golok.pokemontcg.repository

import io.github.golok.pokemontcg.datastore.pokemon.PokemonCardDataStore
import io.github.golok.pokemontcg.model.PokemonCard

class PokemonCardRepository private constructor() : BaseRepository<PokemonCardDataStore>() {
    suspend fun getPokemons(set: String): MutableList<PokemonCard>? {
        val cache = localDataStore?.getPokemons(set)
        if (cache != null) return cache
        val response = remoteDataStore?.getPokemons(set)
        localDataStore?.addAll(set, response)
        return response
    }

    companion object {
        val instance by lazy { PokemonCardRepository() }
    }
}