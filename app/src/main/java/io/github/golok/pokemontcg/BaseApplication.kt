package io.github.golok.pokemontcg

import android.app.Application
import io.github.golok.pokemontcg.database.AppDatabase
import io.github.golok.pokemontcg.datastore.pokemon.PokemonCardRemoteDataStore
import io.github.golok.pokemontcg.datastore.pokemon.PokemonCardRoomDataStore
import io.github.golok.pokemontcg.datastore.set.PokemonSetRemoteDataStore
import io.github.golok.pokemontcg.datastore.set.PokemonSetRoomDataStore
import io.github.golok.pokemontcg.repository.PokemonCardRepository
import io.github.golok.pokemontcg.repository.PokemonSetRepository
import io.github.golok.pokemontcg.webservice.RetrofitApp

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val pokemonTcgService = RetrofitApp.POKEMON_TCG_SERVICE
        val appDatabase = AppDatabase.getInstance(this)
        PokemonSetRepository.instance.apply {
            init(
                PokemonSetRoomDataStore(appDatabase.pokemonSetDao()),
                PokemonSetRemoteDataStore(pokemonTcgService)
            )
        }

        PokemonCardRepository.instance.apply {
            init(
                PokemonCardRoomDataStore(appDatabase.pokemonCardDao()),
                PokemonCardRemoteDataStore(pokemonTcgService)
            )
        }
    }
}