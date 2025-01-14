package com.example.listapaginada.data.repository

import androidx.paging.PagingData
import com.example.listapaginada.data.datasource.local.model.PokemonEntity
import kotlinx.coroutines.flow.Flow

fun interface ListPokemonRepository {
    fun listPokemon(): Flow<PagingData<PokemonEntity>>

}