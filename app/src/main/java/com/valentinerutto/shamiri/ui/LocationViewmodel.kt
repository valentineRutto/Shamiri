package com.valentinerutto.shamiri.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentinerutto.shamiri.data.LocationItem
import com.valentinerutto.shamiri.data.LocationRepository
import com.valentinerutto.shamiri.data.remote.LocationResponse
import com.valentinerutto.shamiri.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationViewmodel(private val repository: LocationRepository):ViewModel() {
    private val _state = MutableStateFlow(LocationUiState(loading = true))
    val state: StateFlow<LocationUiState> = _state.asStateFlow()

    suspend fun getLocations() {
        when (val result = repository.getLocation()) {
            is Resource.Loading -> {

            }

            is Resource.Success -> {

            }

            is Resource.Error -> {

            }

            else -> {

            }
        }
    }


    private fun setState(stateReducer: LocationUiState.() -> LocationUiState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }
}
    data class LocationUiState(
        val loading: Boolean = false,
        val locationItem: List<LocationItem> = emptyList(),
        val error: String = ""
    )

