package com.valentinerutto.shamiri.data

import com.valentinerutto.shamiri.data.remote.CharacterByIdResponse
import com.valentinerutto.shamiri.data.remote.CharactersResponse
import com.valentinerutto.shamiri.data.remote.LocationResponse
import com.valentinerutto.shamiri.utils.orUnknown

fun mapLocationResponseToLocationItem(response: LocationResponse?): List<LocationItem>? {
    return response?.results?.map {
        LocationItem(locationName = it?.name.orUnknown("LocationName"), locationType = it?.type.orUnknown("LocationType"), id = it?.id.orUnknown(0), locationUrl = it?.url.orUnknown("Location URL"))
    }
}fun mapCharacterResponseToResidentsItem(response: CharactersResponse?): List<ResidentsItem>? {
    return response?.results?.map {
        ResidentsItem(
            characterName = it?.name.orUnknown("Name"),
            characterImage = it?.image.orUnknown("Image"),
            id = it?.id.orUnknown(0),
            locationUrl = it?.url.orUnknown("Location URL"),
            characterStatus = it?.status.orUnknown("Status")
        )
    }
}
    fun mapCharacterResponseToResidentsItem(response: CharacterByIdResponse?): List<ResidentsItem>? {
        return response?.map {
            ResidentsItem(
                characterName = it?.name.orUnknown("Name"),
                characterImage = it?.image.orUnknown("Image"),
                id = it?.id.orUnknown(0),
                locationUrl = it?.url.orUnknown("Location URL"),
                characterStatus = it?.status.orUnknown("Status")
            )
        }
    }

