package com.valentinerutto.shamiri.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.valentinerutto.shamiri.data.local.CharacterDao
import com.valentinerutto.shamiri.data.local.CharacterEntity
import com.valentinerutto.shamiri.data.local.LocationDao
import com.valentinerutto.shamiri.data.local.LocationEntity
import com.valentinerutto.shamiri.data.paging.LocationPagingSource
import com.valentinerutto.shamiri.data.remote.ApiService
import com.valentinerutto.shamiri.data.remote.LocationResponse
import com.valentinerutto.shamiri.data.remote.Result
import com.valentinerutto.shamiri.utils.ErrorCodes
import com.valentinerutto.shamiri.utils.Resource
import kotlinx.coroutines.flow.Flow

class LocationRepository(
    private val apiService: ApiService,
    private val locationDao: LocationDao,
    private val residentsDao: CharacterDao
) {
   suspend fun getAllLocations(): Flow<PagingData<Result>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = {LocationPagingSource(apiService) }
    ).flow

    suspend fun getLocation(page: Int): Resource<out List<LocationEntity>?> {
        val response = apiService.getAllLocationsByPage(page)

        if (!response.isSuccessful) {
          //  return Resource.Error("network error")
        }

        val residents = response.body()?.results?.flatMap { it?.residents!! }

//       ?.forEach {
//            it?.substringAfterLast("/")?.toInt()?.let { it1 -> residentsIds.add(it1) }
//
//        }

        getResidentsIds(residents)

        val entity = mapLocationResponseToLocationItem(response.body())
        if (entity != null) {
            locationDao.insertAll(entity)
        }

        return Resource.Success(entity)

    }

    suspend fun getResidentsIds(residents: List<String?>?) {

        val characterIds = residents?.map { it?.split("/")?.last() }

        if (characterIds?.size == 1) {
            getResidents(characterIds.joinToString(",").toList().toString())
        } else if (characterIds!!.isEmpty()) {

            //   _charactersByIds.postValue(NetworkResponse.Success(emptyList()))

        } else {
            getResidents(characterIds.joinToString(","))
        }
    }


    private suspend fun getResidents(ids: String): Resource<List<CharacterEntity>> {

        val response = apiService.getCharactersByIds(ids)

        if (!response.isSuccessful) {
          //  return Resource.Error(ErrorCodes.NETWORK_SEARCH_ERROR)
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
