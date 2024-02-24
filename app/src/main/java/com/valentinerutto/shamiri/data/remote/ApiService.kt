package com.valentinerutto.shamiri.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("character")
    suspend fun getAllCharacters(): Response<CharactersResponse>
    @GET("character/{ids}")
    suspend fun getCharactersByIds(@Path("ids") ids: String): Response<CharacterByIdResponse>
    @GET("location")
    suspend fun getAllLocations(): Response<LocationResponse>
    @GET("episode")
    suspend fun getAllEpisodes(): Response<EpisodesResponse>
}