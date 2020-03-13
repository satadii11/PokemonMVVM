package io.github.golok.pokemontcg.datastore.pokemon

import io.github.golok.pokemontcg.database.PokemonCardDao
import io.github.golok.pokemontcg.model.PokemonCard

class PokemonCardRoomDataStore(private val pokemonCardDao: PokemonCardDao) : PokemonCardDataStore {
    override suspend fun getPokemons(set: String): MutableList<PokemonCard>? {
        val response = pokemonCardDao.getAll(set)
        return if (response.isEmpty()) null else response
    }

    override suspend fun addAll(set: String, pokemons: MutableList<PokemonCard>?) {
        pokemons?.let { pokemonCardDao.insertAll(*it.toTypedArray()) }
    }
}