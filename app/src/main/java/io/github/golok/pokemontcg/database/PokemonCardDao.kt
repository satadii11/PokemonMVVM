package io.github.golok.pokemontcg.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.github.golok.pokemontcg.model.PokemonCard

@Dao
interface PokemonCardDao {
    @Query("SELECT * FROM PokemonCard WHERE `set` = :set")
    suspend fun getAll(set: String): MutableList<PokemonCard>

    @Query("DELETE FROM PokemonCard")
    suspend fun deleteAll()

    @Insert
    suspend fun insertAll(vararg pokemonCards: PokemonCard)
}