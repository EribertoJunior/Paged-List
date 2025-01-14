package com.example.listapaginada.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class ListResponse(
    @SerializedName ("count") val count: Int,
    @SerializedName ("next") val next: String?,
    @SerializedName ("previous") val previous: String?,
    @SerializedName ("results") val results: List<PokemonResponse>
)

data class PokemonResponse(
    @SerializedName ("name") val name: String,
    @SerializedName ("url") val url: String
)
