package io.github.golok.pokemontcg.repository

import io.github.golok.pokemontcg.datastore.pokemon.PokemonDataStore
import io.github.golok.pokemontcg.model.PokemonCard
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class PokemonRepositoryTest {
    @Mock
    var localDataStore: PokemonDataStore? = null

    @Mock
    var remoteDataStore: PokemonDataStore? = null

    var pokemonRepository: PokemonRepository? = null

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        pokemonRepository = PokemonRepository(localDataStore!!, remoteDataStore!!)
    }

    @Test
    fun shouldNotGetPokemonsFromRemoteWhenLocalIsNotNull() {
        runBlocking {
            `when`(localDataStore?.getPokemons(anyString())).thenReturn(mutableListOf<PokemonCard>())
            pokemonRepository?.getPokemons(anyString())

            verify(remoteDataStore, never())?.getPokemons(anyString())
            verify(localDataStore, never())?.addAll(anyString(), any())
        }
    }

    @Test
    fun shouldCallGetPokemonsFromRemoteAndSaveToLocalWhenLocalIsNull() {
        runBlocking {
            `when`(localDataStore?.getPokemons(anyString())).thenReturn(null)
            `when`(remoteDataStore?.getPokemons(anyString())).thenReturn(any())
            pokemonRepository?.getPokemons("Test set")

            verify(remoteDataStore, times(1))?.getPokemons(anyString())
            verify(localDataStore, times(1))?.addAll(anyString(), any())
        }
    }

    @Test
    fun shouldThrowExceptionWhenRemoteThrowAnException() {
        runBlocking {
            `when`(localDataStore?.getPokemons(anyString())).thenReturn(null)
            `when`(remoteDataStore?.getPokemons(anyString())).thenAnswer { throw Exception() }

            try {
                pokemonRepository?.getPokemons("Test set")
            } catch (ex: Exception) {
            }
        }
    }
}