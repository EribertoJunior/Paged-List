package com.example.listapaginada

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.listapaginada.data.datasource.local.model.PokemonEntity
import com.example.listapaginada.domain.usecase.GetPokemonListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val getPokemonListUseCase: GetPokemonListUseCase) : ViewModel() {

    private var _uiState = MutableStateFlow(PagingData.from(emptyList<PokemonEntity>()))
    val uiState = _uiState.asStateFlow()

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            getPokemonListUseCase().cachedIn(viewModelScope).collect {
                _uiState.value = it
            }

        }
    }

}