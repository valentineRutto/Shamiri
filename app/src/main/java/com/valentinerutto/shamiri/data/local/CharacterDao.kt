package com.valentinerutto.shamiri.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import retrofit2.http.GET

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(residents:List<CharacterEntity>)

    @Query("SELECT * FROM residentslist")
    suspend fun getAllResidents():List<CharacterEntity>

}