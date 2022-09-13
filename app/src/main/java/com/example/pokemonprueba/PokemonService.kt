package com.example.pokemonprueba

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonService {
    @GET
    suspend fun getPokemon(@Url url:String) : Response<Pokemon?>
}