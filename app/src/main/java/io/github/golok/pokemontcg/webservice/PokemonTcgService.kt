package io.github.golok.pokemontcg.webservice

import io.github.golok.pokemontcg.model.PokemonCard
import io.github.golok.pokemontcg.model.PokemonSet
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonTcgService {
    @GET("cards")
    suspend fun getCards(@Query("set") set: String): Response<PokemonCard.PokemonCardResponse>

    @GET("sets")
    suspend fun getSets(): Response<PokemonSet.PokemonSetResponse>
}