package io.github.golok.pokemontcg.repository

import io.github.golok.pokemontcg.model.PokemonCard
import io.github.golok.pokemontcg.model.PokemonSet
import io.github.golok.pokemontcg.webservice.PokemonService

class PokemonRepository(private val pokemonService: PokemonService) {
    suspend fun getPokemons(set: String): MutableList<PokemonCard>? {
        val response = pokemonService.getCards(set).await()
        if (response.isSuccessful) {
            return response.body()?.cards
        }

        throw Exception("Something went wrong with status code: ${response.code()}")
    }

    suspend fun getSets(): MutableList<PokemonSet>? {
        val response = pokemonService.getSets().await()
        if (response.isSuccessful) {
            return response.body()?.sets
        }

        throw Exception("Something went wrong with status code: ${response.code()}")
    }
}