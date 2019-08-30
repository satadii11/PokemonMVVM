package io.github.golok.pokemontcg.repository

import io.github.golok.pokemontcg.datastore.set.PokemonSetDataStore
import io.github.golok.pokemontcg.model.PokemonSet
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class PokemonSetRepositoryTest {
    @Mock
    var localDataStore: PokemonSetDataStore? = null

    @Mock
    var remoteDataStore: PokemonSetDataStore? = null

    var pokemonSetRepository: PokemonSetRepository? = null

    var pokemonSets = mutableListOf<PokemonSet>()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        pokemonSetRepository = PokemonSetRepository.instance.apply {
            init(localDataStore!!, remoteDataStore!!)
        }
    }

    @Test
    fun shouldNotGetPokemonsFromRemoteWhenLocalIsNotNull() {
        runBlocking {
            `when`(localDataStore?.getSets()).thenReturn(pokemonSets)
            pokemonSetRepository?.getSets()

            verify(remoteDataStore, never())?.getSets()
            verify(localDataStore, never())?.addAll(pokemonSets)
        }
    }

    @Test
    fun shouldCallGetPokemonsFromRemoteAndSaveToLocalWhenLocalIsNull() {
        runBlocking {
            `when`(localDataStore?.getSets()).thenReturn(null)
            `when`(remoteDataStore?.getSets()).thenReturn(pokemonSets)
            pokemonSetRepository?.getSets()

            verify(remoteDataStore, times(1))?.getSets()
            verify(localDataStore, times(1))?.addAll(pokemonSets)
        }
    }

    @Test
    fun shouldThrowExceptionWhenRemoteThrowAnException() {
        runBlocking {
            `when`(localDataStore?.getSets()).thenReturn(null)
            `when`(remoteDataStore?.getSets()).thenAnswer { throw Exception() }

            try {
                pokemonSetRepository?.getSets()
            } catch (ex: Exception) {
            }
        }
    }
}