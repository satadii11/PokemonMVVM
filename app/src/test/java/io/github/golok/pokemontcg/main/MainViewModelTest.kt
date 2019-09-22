package io.github.golok.pokemontcg.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.golok.pokemontcg.repository.PokemonSetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MainViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    var pokemonSetRepository: PokemonSetRepository? = null

    var mainViewModel: MainViewModel? = null

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel(pokemonSetRepository!!)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun shouldLoadingWhenFirstInitialized() {
        val state = mainViewModel!!.viewState.value!!
        assertTrue(state.loading)
        assertNull(state.error)
        assertNull(state.data)
    }

    @Test
    fun shouldStopLoadingAndGiveDataWhenSuccess() {
        runBlocking {
            `when`(pokemonSetRepository?.getSets()).thenReturn(mutableListOf())
            mainViewModel?.getSets()
            val state = mainViewModel!!.viewState.value!!
            assertFalse(state.loading)
            assertNull(state.error)
            assertNotNull(state.data)
        }
    }

    @Test
    fun shouldThrowErrorWhenRepositoryIsThrowingError() {
        runBlocking {
            `when`(pokemonSetRepository?.getSets()).thenAnswer { throw Exception() }
            mainViewModel?.getSets()
            val state = mainViewModel!!.viewState.value!!
            assertFalse(state.loading)
            assertNotNull(state.error)
            assertNull(state.data)
        }
    }
}