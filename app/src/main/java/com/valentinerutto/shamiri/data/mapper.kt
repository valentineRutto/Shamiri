package com.valentinerutto.shamiri.data

import com.valentinerutto.shamiri.data.local.CharacterEntity
import com.valentinerutto.shamiri.data.local.LocationEntity
import com.valentinerutto.shamiri.data.remote.CharacterByIdResponse
import com.valentinerutto.shamiri.data.remote.CharactersResponse
import com.valentinerutto.shamiri.data.remote.LocationResponse
import com.valentinerutto.shamiri.utils.orUnknown

fun mapLocationResponseToLocationItem(response: LocationResponse?): List<LocationEntity>? {
    return response?.results?.map {
        LocationEntity(locationName = it?.name.orUnknown("LocationName"), locationType = it?.type.orUnknown("LocationType"), id = it?.id.orUnknown(0), locationUrl = it?.url.orUnknown("Location URL"))
    }
}fun mapCharacterResponseToCharacterEntity(response: CharactersResponse?): List<CharacterEntity>? {
    return response?.results?.map {
        CharacterEntity(
            characterName = it?.name.orUnknown("Name"),
            characterImage = it?.image.orUnknown("Image"),
            id = it?.id.orUnknown(0),
            locationUrl = it?.url.orUnknown("Location URL"),
            characterStatus = it?.status.orUnknown("Status")
        )
    }
}
    fun mapCharacterResponseToResidentsItem(response: CharacterByIdResponse?): List<CharacterEntity>? {
        return response?.map {
            CharacterEntity(
                characterName = it?.name.orUnknown("Name"),
                characterImage = it?.image.orUnknown("Image"),
                id = it?.id.orUnknown(0),
                locationUrl = it?.url.orUnknown("Location URL"),
                characterStatus = it?.status.orUnknown("Status")
            )
        }
    }

