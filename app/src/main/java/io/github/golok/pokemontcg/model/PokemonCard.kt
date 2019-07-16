package io.github.golok.pokemontcg.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonCard(
    var name: String,
    @SerializedName("imageUrl") var image: String,
    var rarity: String,
    var series: String
) : Parcelable {
    data class PokemonCardResponse(
        var cards: MutableList<PokemonCard>
    )
}