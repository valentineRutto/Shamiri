package com.valentinerutto.shamiri.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class LocationEntity (
var locationName: String,
var locationType: String,
@PrimaryKey
var id: Int,
var locationUrl: String
)