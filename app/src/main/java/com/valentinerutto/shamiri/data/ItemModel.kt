package com.valentinerutto.shamiri.data

import androidx.room.Entity
import androidx.room.PrimaryKey

data class LocationItem(
    var locationName: String,
    var locationType: String,
    var id: Int,
    var locationUrl: String
)
data class ResidentsItem(
    var characterName: String,
    var characterStatus: String,
    var characterImage: String,
    var id: Int,
    var locationUrl: String
)
data class ResidentLocationItem(
    var locationName: String,
    var locationType: String,
    var characterName: String,
    var characterStatus: String,
    var characterImage: String,
    var id: Int,
    var locationUrl: String
)

