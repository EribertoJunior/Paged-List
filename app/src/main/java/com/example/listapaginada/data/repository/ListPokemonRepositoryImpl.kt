package com.example.listapaginada.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.listapaginada.data.datasource.PokemonRemoteMediator
import com.example.listapaginada.data.datasource.local.dao.PokemonDao
import com.example.listapaginada.data.datasource.local.model.PokemonEntity
import kotlinx.coroutines.flow.Flow

class ListPokemonRepositoryImpl(private val pokemonRemoteMediator: PokemonRemoteMediator,
                                private val pokemonDao: PokemonDao) :
    ListPokemonRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun listPokemon(): Flow<PagingData<PokemonEntity>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = pokemonRemoteMediator,
            pagingSourceFactory = { pokemonDao.getAll() }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 1000
    }
}