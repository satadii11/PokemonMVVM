package io.github.golok.pokemontcg.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.golok.pokemontcg.R
import io.github.golok.pokemontcg.model.PokemonSet
import io.github.golok.pokemontcg.repository.PokemonRepository
import io.github.golok.pokemontcg.webservice.RetrofitApp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var vm: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainAdapter()
        rvSet.adapter = adapter

        val factory = MainViewModelFactory(PokemonRepository(RetrofitApp.pokemonService))
        vm = ViewModelProviders.of(this, factory).get(MainViewModel::class.java).apply {
            viewState.observe(this@MainActivity, Observer(this@MainActivity::handleState))
            getSets()
            srlSet.setOnRefreshListener { getSets() }
        }
    }

    private fun handleState(viewState: MainViewState?) {
        viewState?.let {
            toggleLoading(it.loading)
            it.data?.let { data -> showData(data) }
            it.error?.let { error -> showError(error) }
        }
    }

    private fun showData(data: MutableList<PokemonSet>) {
        adapter.updateData(data)
        tvSetError.visibility = View.GONE
        rvSet.visibility = View.VISIBLE
    }

    private fun showError(error: Exception) {
        tvSetError.text = error.message
        tvSetError.visibility = View.VISIBLE
        rvSet.visibility = View.GONE
    }

    private fun toggleLoading(loading: Boolean) {
        srlSet.isRefreshing = loading
    }
}
