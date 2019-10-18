package io.github.golok.pokemontcg.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import io.github.golok.pokemontcg.R
import io.github.golok.pokemontcg.model.PokemonCard
import kotlinx.android.synthetic.main.fragment_pokemon_card_detail.*

class PokemonCardDetailFragment : Fragment() {
    private lateinit var vm: PokemonCardDetailViewModel

    private val args: PokemonCardDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_pokemon_card_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonCard = args.pokemonCard
        vm = ViewModelProviders.of(this).get(PokemonCardDetailViewModel::class.java).apply {
            viewState.observe(
                this@PokemonCardDetailFragment,
                Observer(this@PokemonCardDetailFragment::handleState)
            )
            pokemonCard?.let { setData(it) }
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
}
