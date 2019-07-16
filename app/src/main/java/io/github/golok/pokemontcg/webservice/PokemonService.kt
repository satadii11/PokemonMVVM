package io.github.golok.pokemontcg.webservice

import io.github.golok.pokemontcg.model.PokemonCard
import io.github.golok.pokemontcg.model.PokemonSet
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {
    @GET("cards")
    fun getCards(@Query("set") set: String): Deferred<Response<PokemonCard.PokemonCardResponse>>

    @GET("sets")
    fun getSets(): Deferred<Response<PokemonSet.PokemonSetResponse>>
}