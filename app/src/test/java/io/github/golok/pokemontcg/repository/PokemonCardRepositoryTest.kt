package io.github.golok.pokemontcg.repository

import io.github.golok.pokemontcg.datastore.pokemon.PokemonCardDataStore
import io.github.golok.pokemontcg.model.PokemonCard
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class PokemonCardRepositoryTest {
    @Mock
    var localCardDataStore: PokemonCardDataStore? = null

    @Mock
    var remoteCardDataStore: PokemonCardDataStore? = null

    var pokemonCardRepository: PokemonCardRepository? = null

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        pokemonCardRepository = PokemonCardRepository.instance.apply {
            init(localCardDataStore!!, remoteCardDataStore!!)
        }
    }

    @Test
    fun shouldNotGetPokemonsFromRemoteWhenLocalIsNotNull() {
        runBlocking {
            `when`(localCardDataStore?.getPokemons(anyString())).thenReturn(mutableListOf())
            pokemonCardRepository?.getPokemons(anyString())

            verify(remoteCardDataStore, never())?.getPokemons(anyString())
            verify(localCardDataStore, never())?.addAll(anyString(), any())
        }
    }

    @Test
    fun shouldCallGetPokemonsFromRemoteAndSaveToLocalWhenLocalIsNull() {
        runBlocking {
            `when`(localCardDataStore?.getPokemons(anyString())).thenReturn(null)
            `when`(remoteCardDataStore?.getPokemons(anyString())).thenReturn(any())
            pokemonCardRepository?.getPokemons("Test set")

            verify(remoteCardDataStore, times(1))?.getPokemons(anyString())
            verify(localCardDataStore, times(1))?.addAll(anyString(), any())
        }
    }

    @Test
    fun shouldThrowExceptionWhenRemoteThrowAnException() {
        runBlocking {
            `when`(localCardDataStore?.getPokemons(anyString())).thenReturn(null)
            `when`(remoteCardDataStore?.getPokemons(anyString())).thenAnswer { throw Exception() }

            try {
                pokemonCardRepository?.getPokemons("Test set")
            } catch (ex: Exception) {
            }
        }
    }
}