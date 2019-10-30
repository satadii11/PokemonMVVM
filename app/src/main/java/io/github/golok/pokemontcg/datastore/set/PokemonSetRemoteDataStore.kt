package io.github.golok.pokemontcg.datastore.set

import io.github.golok.pokemontcg.model.PokemonSet
import io.github.golok.pokemontcg.webservice.PokemonTcgService

class PokemonSetRemoteDataStore(private val pokemonTcgService: PokemonTcgService) :
    PokemonSetDataStore {
    override suspend fun getSets(): MutableList<PokemonSet>? {
        val response = pokemonTcgService.getSets()
        if (response.isSuccessful) return response.body()?.sets

        throw Exception("Terjadi kesalahan saat melakukan request data, status error ${response.code()}")
    }

    override suspend fun addAll(sets: MutableList<PokemonSet>?) {
    }
}