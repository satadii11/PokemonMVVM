package io.github.golok.pokemontcg.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.golok.pokemontcg.model.PokemonCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.any

class PokemonCardDetailViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    var pokemonCardDetailViewModel: PokemonCardDetailViewModel? = null

    @Before
    fun init() {
        pokemonCardDetailViewModel = PokemonCardDetailViewModel()
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun shouldNullWhenFirstInitialized() {
        val state = pokemonCardDetailViewModel!!.viewState.value!!
        assertNull(state.data)
    }

    @Test
    fun shouldNotNullAfterSetData() {
        pokemonCardDetailViewModel?.setData(PokemonCard("", "", "", ""))
        val state = pokemonCardDetailViewModel!!.viewState.value!!
        assertNotNull(state.data)
    }
}