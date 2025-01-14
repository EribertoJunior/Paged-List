package com.example.listapaginada.domain.usecase

import androidx.paging.PagingData
import com.example.listapaginada.data.datasource.local.model.PokemonEntity
import com.example.listapaginada.data.repository.ListPokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCase(private val listPokemonRepository: ListPokemonRepository) {

    operator fun invoke(): Flow<PagingData<PokemonEntity>> {
        return listPokemonRepository.listPokemon()

    }
}