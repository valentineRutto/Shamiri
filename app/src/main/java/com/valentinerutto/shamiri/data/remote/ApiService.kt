package com.valentinerutto.shamiri.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    suspend fun getAllCharacters(): Response<CharactersResponse>
    @GET("location")
    suspend fun getAllLocations(): Response<LocationResponse>
    @GET("episode")
    suspend fun getAllEpisodes(): Response<EpisodesResponse>
}