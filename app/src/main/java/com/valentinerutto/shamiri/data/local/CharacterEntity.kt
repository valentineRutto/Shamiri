package com.valentinerutto.shamiri.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ResidentsList")
data class CharacterEntity (
    var characterName: String,
    var characterStatus: String,
    var characterImage: String,
    @PrimaryKey
    var id: Int,
    var locationUrl: String
    )