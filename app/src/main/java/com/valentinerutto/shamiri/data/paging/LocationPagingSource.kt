package com.valentinerutto.shamiri.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.valentinerutto.shamiri.data.LocationRepository
import com.valentinerutto.shamiri.data.remote.LocationResponse
import com.valentinerutto.shamiri.utils.Constants
import com.valentinerutto.shamiri.utils.Resource

class LocationPagingSource(
    private val repository: LocationRepository
) : PagingSource<Int, LocationResponse.Result>() {
    override fun getRefreshKey(state: PagingState<Int, LocationResponse.Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationResponse.Result> {
        val currentPage = params.key ?: Constants.FIRST_PAGE_INDEX
        return when (val result = repository.getPagedLocation(currentPage)) {
            is Resource.Error -> LoadResult.Error(Exception(result.errorMessage))
            is Resource.Loading -> LoadResult.Error(Exception())
            is Resource.Success -> LoadResult.Page(
                data = result.data,
                prevKey = if (currentPage == Constants.FIRST_PAGE_INDEX) null else currentPage - 1,
                nextKey = if (result.data.isNullOrEmpty()) null else currentPage + 1
            )
        }

    }


}