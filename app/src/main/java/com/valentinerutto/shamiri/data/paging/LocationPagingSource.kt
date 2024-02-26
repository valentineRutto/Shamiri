package com.valentinerutto.shamiri.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.valentinerutto.shamiri.data.LocationRepository
import com.valentinerutto.shamiri.data.remote.Result
import com.valentinerutto.shamiri.utils.Constants

class LocationPagingSource(
    private val repository: LocationRepository
) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult.Page<Int, Result> {
        val currentPage = params.key ?: Constants.FIRST_PAGE_INDEX
        val response = repository.getPagedLocation(currentPage)
//        return when (val result = repository.getPagedLocation(currentPage)) {
//            is Resource.Error -> LoadResult.Error(Exception(result.errorMessage))
//            is Resource.Loading -> LoadResult.Error(Exception())
//            is Resource.Success -> Page(
//                data = result.data.results,
//                prevKey = if (currentPage == Constants.FIRST_PAGE_INDEX) null else currentPage - 1,
//                nextKey = if (result.data.results.isNullOrEmpty()) null else currentPage + 1
//            )
//        }
    return LoadResult.Page(
        data = response?.results,
        prevKey = if (currentPage == Constants.FIRST_PAGE_INDEX) null else currentPage - 1,
        nextKey = if (response?.results?.isNotEmpty() == true) response.info?.pages?.plus(1) else null
    )
    }


}