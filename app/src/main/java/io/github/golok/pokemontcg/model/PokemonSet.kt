package io.github.golok.pokemontcg.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class PokemonSet(
    var name: String,
    @SerializedName("logoUrl") var logo: String,
    @PrimaryKey(autoGenerate = true) var idSet: Int = 0
) {
    data class PokemonSetResponse(
        var sets: MutableList<PokemonSet>
    )
}