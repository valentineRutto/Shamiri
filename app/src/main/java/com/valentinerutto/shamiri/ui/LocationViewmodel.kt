package com.valentinerutto.shamiri.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.valentinerutto.shamiri.data.LocationRepository
import com.valentinerutto.shamiri.data.ResidentLocationItem
import com.valentinerutto.shamiri.data.paging.LocationPagingSource
import com.valentinerutto.shamiri.utils.Constants
import com.valentinerutto.shamiri.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationViewmodel(private val repository: LocationRepository) : ViewModel() {
    private val _state = MutableStateFlow(LocationUiState(loading = true))
    val state: StateFlow<LocationUiState> = _state.asStateFlow()
init{
    viewModelScope.launch {
   // getLocations()
}}
    val locationsPager = Pager(PagingConfig(pageSize = 20)) {
        LocationPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

    suspend fun getLocations() {

        when (val result = repository.getLocation(Constants.FIRST_PAGE_INDEX)) {
            is Resource.Loading -> {
                setState {
                    copy(loading = true)
                }

            }

            is Resource.Success -> {

                val savedItem = repository.getResidentwithLocation()

                setState {
                    copy(loading = false, locationItem = savedItem)
                }

            }

            is Resource.Error -> {
                setState { copy(loading = false, error = result.errorMessage) }

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
    val locationItem: List<ResidentLocationItem> = emptyList(),
    val error: String = ""
)

