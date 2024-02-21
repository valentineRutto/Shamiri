package com.valentinerutto.shamiri.data

import com.valentinerutto.shamiri.data.remote.ApiService

class LocationRepository(private val apiService: ApiService) {
    suspend fun getLocation(){
        val response = apiService.getAllLocations()

    }
}