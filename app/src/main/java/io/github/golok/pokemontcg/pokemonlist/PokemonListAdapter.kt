package io.github.golok.pokemontcg.pokemonlist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.golok.pokemontcg.R
import io.github.golok.pokemontcg.detail.PokemonCardDetailActivity
import io.github.golok.pokemontcg.model.PokemonCard
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_pokemon.*

class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {
    private val pokemonCards = mutableListOf<PokemonCard>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
    )

    override fun getItemCount() = pokemonCards.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(pokemonCards[position])
    }

    fun updateData(newPokemonCards: MutableList<PokemonCard>) {
        pokemonCards.clear()
        pokemonCards.addAll(newPokemonCards)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bindItem(pokemonCard: PokemonCard) {
            Glide.with(containerView!!)
                .load(pokemonCard.image)
                .into(ivCardLogo)

            tvCardName.text = pokemonCard.name
            tvCardRarity.text = pokemonCard.rarity

            containerView?.setOnClickListener {
                containerView?.context?.let {
                    val intent = Intent(it, PokemonCardDetailActivity::class.java)
                        .putExtra(PokemonCardDetailActivity.EXTRA_POKEMON, pokemonCard)
                    it.startActivity(intent)
                }
            }
        }
    }
}