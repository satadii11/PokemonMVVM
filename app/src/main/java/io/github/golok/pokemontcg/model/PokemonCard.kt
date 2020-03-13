package io.github.golok.pokemontcg.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class PokemonCard(
    var name: String?,
    @SerializedName("imageUrl") var image: String?,
    var rarity: String?,
    var series: String?,
    var set: String?,
    @PrimaryKey(autoGenerate = true) var idCard: Int = 0
) : Parcelable {
    data class PokemonCardResponse(
        var cards: MutableList<PokemonCard>
    )
}