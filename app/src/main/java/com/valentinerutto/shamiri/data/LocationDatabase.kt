package com.valentinerutto.shamiri.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.valentinerutto.shamiri.data.local.CharacterDao
import com.valentinerutto.shamiri.data.local.CharacterEntity
import com.valentinerutto.shamiri.data.local.LocationDao
import com.valentinerutto.shamiri.data.local.LocationEntity
import com.valentinerutto.shamiri.utils.Constants

@Database(
    entities = [LocationEntity::class,CharacterEntity::class],

    version = 1,

    exportSchema = false
)
abstract class LocationDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
    abstract fun residentsDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: LocationDatabase? = null

        fun getDatabase(context: Context): LocationDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, LocationDatabase::class.java, Constants.DB_NAME
                ).allowMainThreadQueries()
                    // Wipes and rebuilds instead of migrating if no M¬igration object.
                    .fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}