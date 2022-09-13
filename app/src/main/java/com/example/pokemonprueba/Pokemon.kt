package com.example.pokemonprueba

import com.google.gson.annotations.SerializedName


data class Pokemon (
    @SerializedName("id"                       ) var id                     : Int,
    @SerializedName("name"                     ) var name                   : String,
    @SerializedName("sprites"                  ) var sprites                : Sprites,
)
data class Sprites (
    @SerializedName("back_default"       ) var backDefault      : String,
    @SerializedName("front_default"      ) var frontDefault     : String,
)
