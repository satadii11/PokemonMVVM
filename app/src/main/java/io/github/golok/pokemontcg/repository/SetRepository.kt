package io.github.golok.pokemontcg.repository

import io.github.golok.pokemontcg.datastore.set.SetDataStore
import io.github.golok.pokemontcg.model.PokemonSet

class SetRepository(
    private val setLocalDataStore: SetDataStore,
    private val setRemoteDataStore: SetDataStore
) {
    suspend fun getSets(): MutableList<PokemonSet>? {
        val cache = setLocalDataStore.getSets()
        if (cache != null) return cache
        val response = setRemoteDataStore.getSets()
        setLocalDataStore.addAll(response)
        return response
    }
}
