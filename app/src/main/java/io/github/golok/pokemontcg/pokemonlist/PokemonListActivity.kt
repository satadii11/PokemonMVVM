package io.github.golok.pokemontcg.pokemonlist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.golok.pokemontcg.R
import io.github.golok.pokemontcg.model.PokemonCard
import io.github.golok.pokemontcg.repository.PokemonCardRepository
import kotlinx.android.synthetic.main.activity_pokemon_list.*

class PokemonListActivity : AppCompatActivity() {
    private lateinit var vm: PokemonListViewModel
    private lateinit var adapter: PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)

        val set = intent.getStringExtra(EXTRA_SET)

        adapter = PokemonListAdapter()
        rvCard.adapter = adapter

        val factory = PokemonListViewModelFactory(PokemonCardRepository.instance)
        vm = ViewModelProviders.of(this, factory).get(PokemonListViewModel::class.java).apply {
            viewState.observe(
                this@PokemonListActivity,
                Observer(this@PokemonListActivity::handleState)
            )
            getPokemons(set!!)
            srlCard.setOnRefreshListener { getPokemons(set) }
        }
    }

    private fun handleState(viewState: PokemonListViewState?) {
        viewState?.let {
            toggleLoading(it.loading)
            it.data?.let { data -> showData(data) }
            it.error?.let { error -> showError(error) }
        }
    }

    private fun showData(data: MutableList<PokemonCard>) {
        adapter.updateData(data)
        tvCardError.visibility = View.GONE
        rvCard.visibility = View.VISIBLE
    }

    private fun showError(error: Exception) {
        tvCardError.text = error.message
        tvCardError.visibility = View.VISIBLE
        rvCard.visibility = View.GONE
    }

    private fun toggleLoading(loading: Boolean) {
        srlCard.isRefreshing = loading
    }

    companion object {
        const val EXTRA_SET = "extra:set"
    }
}
