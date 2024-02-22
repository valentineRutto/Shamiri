package com.valentinerutto.shamiri.data

import androidx.core.text.isDigitsOnly
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
        val residentsIds = mutableListOf<Int>()
        response.body()?.results?.flatMap { it?.residents!! }?.forEach {
            it?.substringAfterLast("/")?.toInt()?.let { it1 -> residentsIds.add(it1) }

       }
        getResidents(residentsIds)

        return Resource.Success(mapLocationResponseToLocationItem(response.body()))

    }
    suspend fun getAllCharacters( ): Resource<List<ResidentsItem>?> {
        val response = apiService.getAllCharacters()

        if (!response.isSuccessful) {
            return Resource.Error("network error")

        }
        return Resource.Success(mapCharacterResponseToResidentsItem(response.body()))

    }

    suspend fun getResidents(ids:List<Int>):Resource<List<ResidentsItem>> {

        val response = apiService.getCharactersByIds(ids)
        if (!response.isSuccessful) {
            return Resource.Error("network error")

        }
        return Resource.Success(mapCharacterResponseToResidentsItem(response.body())!!)

    }
}