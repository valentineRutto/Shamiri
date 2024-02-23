package com.valentinerutto.shamiri.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import retrofit2.http.GET

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(locations:List<LocationEntity>)

    @GET
    suspend fun getAllLocations():List<LocationEntity>

}