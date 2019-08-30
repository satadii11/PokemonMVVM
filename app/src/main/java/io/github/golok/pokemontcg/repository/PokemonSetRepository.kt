package io.github.golok.pokemontcg.repository

import io.github.golok.pokemontcg.datastore.set.PokemonSetDataStore
import io.github.golok.pokemontcg.datastore.set.PokemonSetRemoteDataStore
import io.github.golok.pokemontcg.model.PokemonSet

class PokemonSetRepository private constructor() : BaseRepository<PokemonSetDataStore>() {
    suspend fun getSets(): MutableList<PokemonSet>? {
        val cache = localDataStore?.getSets()
        if (cache != null) return cache
        val response = remoteDataStore?.getSets()
        localDataStore?.addAll(response)
        return response
    }

    companion object {
        val instance by lazy { PokemonSetRepository() }
    }
}

