package com.valentinerutto.shamiri.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(locations:List<LocationEntity>)

    @Query("SELECT * FROM locationList")
    suspend fun getAllLocations():List<LocationEntity>

}