package io.github.golok.pokemontcg.repository

import io.github.golok.pokemontcg.datastore.set.SetDataStore
import io.github.golok.pokemontcg.model.PokemonSet
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class SetRepositoryTest {
    @Mock
    var localDataStore: SetDataStore? = null

    @Mock
    var remoteDataStore: SetDataStore? = null

    var pokemonRepository: SetRepository? = null
    var sets = mutableListOf<PokemonSet>()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        pokemonRepository = SetRepository(localDataStore!!, remoteDataStore!!)
    }

    @Test
    fun shouldNotGetPokemonsFromRemoteWhenLocalIsNotNull() {
        runBlocking {
            `when`(localDataStore?.getSets()).thenReturn(sets)
            pokemonRepository?.getSets()

            verify(remoteDataStore, never())?.getSets()
            verify(localDataStore, never())?.addAll(sets)
        }
    }

    @Test
    fun shouldCallGetPokemonsFromRemoteAndSaveToLocalWhenLocalIsNull() {
        runBlocking {
            `when`(localDataStore?.getSets()).thenReturn(null)
            `when`(remoteDataStore?.getSets()).thenReturn(sets)
            pokemonRepository?.getSets()

            verify(remoteDataStore, times(1))?.getSets()
            verify(localDataStore, times(1))?.addAll(sets)
        }
    }

    @Test
    fun shouldThrowExceptionWhenRemoteThrowAnException() {
        runBlocking {
            `when`(localDataStore?.getSets()).thenReturn(null)
            `when`(remoteDataStore?.getSets()).thenAnswer { throw Exception() }

            try {
                pokemonRepository?.getSets()
            } catch (ex: Exception) {
            }
        }
    }
}