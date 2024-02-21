package com.valentinerutto.shamiri.data

import com.valentinerutto.shamiri.data.remote.ApiService
import com.valentinerutto.shamiri.utils.Resource
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext

class LocationRepository(private val apiService: ApiService) {
    suspend fun getLocation(): Resource<List<LocationItem>?> {
        val response = apiService.getAllLocations()

        if (!response.isSuccessful) {
            return Resource.Error("network error")
        }
        return Resource.Success(mapLocationResponseToLocationItem(response.body()))

    }

    suspend fun getCharacters(): Resource<List<ResidentsItem>?> {
        val response = apiService.getAllCharacters()

        if (!response.isSuccessful) {
            return Resource.Error("network error")
        }
        return Resource.Success(mapCharacterResponseToResidentsItem(response.body()))

    }

    suspend fun getResidents() {

        val locationResource = withContext(NonCancellable) {
            getLocation()
        }

        val characters = getCharacters()


    }
}