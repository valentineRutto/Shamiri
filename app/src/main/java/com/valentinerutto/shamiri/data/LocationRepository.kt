package com.valentinerutto.shamiri.data

import com.valentinerutto.shamiri.data.local.CharacterDao
import com.valentinerutto.shamiri.data.local.CharacterEntity
import com.valentinerutto.shamiri.data.local.LocationDao
import com.valentinerutto.shamiri.data.local.LocationEntity
import com.valentinerutto.shamiri.data.remote.ApiService
import com.valentinerutto.shamiri.utils.Resource

class LocationRepository(
    private val apiService: ApiService,
    private val locationDao: LocationDao,
    private val residentsDao: CharacterDao
) {
    private val residentsIds = mutableListOf<Int>()

    suspend fun getLocation(): Resource<List<LocationEntity>?> {
        val response = apiService.getAllLocations()

        if (!response.isSuccessful) {
            return Resource.Error("network error")
        }

        response.body()?.results?.flatMap { it?.residents!! }?.forEach {
            it?.substringAfterLast("/")?.toInt()?.let { it1 -> residentsIds.add(it1) }

        }

        val entity = mapLocationResponseToLocationItem(response.body())
        if (entity != null) {
            locationDao.insertAll(entity)
        }

        getResidents(residentsIds)

        return Resource.Success(entity)

    }

    suspend fun getAllCharacters(): Resource<List<CharacterEntity>?> {
        val response = apiService.getAllCharacters()

        if (!response.isSuccessful) {
            return Resource.Error("network error")

        }
        return Resource.Success(mapCharacterResponseToCharacterEntity(response.body()))

    }

    private suspend fun getResidents(ids: List<Int>): Resource<List<CharacterEntity>> {

        val response = apiService.getCharactersByIds(ids)
        if (!response.isSuccessful) {
            return Resource.Error("network error")
        }
        val entity = mapCharacterResponseToResidentsItem(response.body())
        if (entity != null) {
            residentsDao.insertAll(entity)
        }

        return Resource.Success(entity!!)

    }


    suspend fun getResidentwithLocation(): List<ResidentLocationItem> {
        val locationList: List<LocationEntity> = locationDao.getAllLocations()
        val residentsItemList: List<CharacterEntity> = residentsDao.getAllResidents()
        val residentLocation = mutableListOf<ResidentLocationItem>()

        for (location in locationList) {
            val residents = residentsItemList.find { it.locationUrl == location.locationUrl }
            if (residents != null) {
                val combinedDataClass = ResidentLocationItem(
                    locationName = location.locationName,
                    locationType = location.locationType,
                    characterName = residents.characterName,
                    characterStatus = residents.characterStatus,
                    characterImage = residents.characterImage,
                    id = location.id,
                    locationUrl = location.locationUrl
                )
                residentLocation.add(combinedDataClass)
            }
        }
        return residentLocation
    }
}
