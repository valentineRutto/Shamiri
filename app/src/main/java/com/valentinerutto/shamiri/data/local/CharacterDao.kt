package com.valentinerutto.shamiri.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import retrofit2.http.GET

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(residents:List<CharacterEntity>)

    @GET
    suspend fun getAllResidents():List<CharacterEntity>

}