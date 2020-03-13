package io.github.golok.pokemontcg.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.golok.pokemontcg.model.PokemonCard
import io.github.golok.pokemontcg.model.PokemonSet

@Database(entities = [PokemonCard::class, PokemonSet::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonCardDao(): PokemonCardDao
    abstract fun pokemonSetDao(): PokemonSetDao

    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            instance?.let { return it }
            instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "pokemon-card"
            ).build()
            return instance!!
        }
    }
}