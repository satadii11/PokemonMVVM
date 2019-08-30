package io.github.golok.pokemontcg

import android.app.Application
import io.github.golok.pokemontcg.datastore.pokemon.PokemonCardLocalDataStore
import io.github.golok.pokemontcg.datastore.pokemon.PokemonCardRemoteDataStore
import io.github.golok.pokemontcg.datastore.set.PokemonSetLocalDataStore
import io.github.golok.pokemontcg.datastore.set.PokemonSetRemoteDataStore
import io.github.golok.pokemontcg.repository.PokemonCardRepository
import io.github.golok.pokemontcg.repository.PokemonSetRepository
import io.github.golok.pokemontcg.webservice.RetrofitApp

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val pokemonTcgService = RetrofitApp.POKEMON_TCG_SERVICE
        PokemonSetRepository.instance.apply {
            init(PokemonSetLocalDataStore(), PokemonSetRemoteDataStore(pokemonTcgService))
        }

        PokemonCardRepository.instance.apply {
            init(PokemonCardLocalDataStore(), PokemonCardRemoteDataStore(pokemonTcgService))
        }
    }
}