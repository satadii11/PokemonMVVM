package io.github.golok.pokemontcg.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import io.github.golok.pokemontcg.R
import io.github.golok.pokemontcg.model.PokemonCard
import kotlinx.android.synthetic.main.activity_pokemon_card_detail.*

class PokemonCardDetailActivity : AppCompatActivity() {
    private lateinit var vm: PokemonCardDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_card_detail)

        val pokemonCard = intent.getParcelableExtra<PokemonCard>(EXTRA_POKEMON)
        vm = ViewModelProviders.of(this).get(PokemonCardDetailViewModel::class.java).apply {
            viewState.observe(
                this@PokemonCardDetailActivity,
                Observer(this@PokemonCardDetailActivity::handleState)
            )
            setData(pokemonCard)
        }
    }

    private fun handleState(viewState: PokemonCardDetailViewState) {
        viewState.data?.let { showDetail(it) }
    }

    private fun showDetail(data: PokemonCard) {
        Glide.with(this)
            .load(data.image)
            .into(ivLogo)

        tvName.text = data.name
        tvRarity.text = data.rarity
        tvSeries.text = data.series
    }

    companion object {
        const val EXTRA_POKEMON = "extra:pokemon"
    }
}
