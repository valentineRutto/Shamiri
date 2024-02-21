package com.valentinerutto.shamiri.data

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
