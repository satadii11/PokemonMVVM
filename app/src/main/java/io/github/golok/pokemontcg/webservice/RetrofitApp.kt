package io.github.golok.pokemontcg.webservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApp {
    private const val BASE_URL = "https://api.pokemontcg.io/v1/"

    private val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val POKEMON_TCG_SERVICE: PokemonTcgService = client.create(PokemonTcgService::class.java)
}