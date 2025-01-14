package com.example.listapaginada.data.datasource.remote.service

import com.example.listapaginada.data.datasource.remote.model.ListResponse
import retrofit2.http.GET
import retrofit2.http.Query

fun interface ListPokemonService {
    @GET("pokemon")
    suspend fun listPokemon(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): ListResponse
}