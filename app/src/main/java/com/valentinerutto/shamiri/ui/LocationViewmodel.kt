package com.valentinerutto.shamiri.ui

import androidx.lifecycle.ViewModel
import com.valentinerutto.shamiri.data.LocationRepository

class LocationViewmodel(private val repository: LocationRepository):ViewModel() {
    suspend fun getLocations(){
        repository.getLocation()
    }

}