package io.github.golok.pokemontcg.datastore.pokemon

import io.github.golok.pokemontcg.model.PokemonCard
import io.github.golok.pokemontcg.webservice.PokemonTcgService

class PokemonRemoteDataStore(private val pokemonTcgService: PokemonTcgService) : PokemonDataStore {
    override suspend fun getPokemons(set: String): MutableList<PokemonCard>? {
        val response = pokemonTcgService.getCards(set)
        if (response.isSuccessful) return response.body()?.cards

        throw Exception("Terjadi kesalahan saat melakukan request data, status error ${response.code()}")
    }

    override suspend fun addAll(set: String, pokemons: MutableList<PokemonCard>?) {
    }
}
