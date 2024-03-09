package com.valentinerutto.shamiri.data.paging

import android.app.appsearch.SearchResults
import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.valentinerutto.shamiri.data.LocationRepository
import com.valentinerutto.shamiri.data.remote.ApiService
import com.valentinerutto.shamiri.data.remote.Result
import com.valentinerutto.shamiri.utils.Constants
import com.valentinerutto.shamiri.utils.ErrorCodes
import com.valentinerutto.shamiri.utils.Resource
import okhttp3.internal.http2.ErrorCode
import java.io.IOException
import java.net.SocketTimeoutException

class LocationPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val pageNumber = params.key ?: 1
        return try {
            val response = apiService.getAllLocationsByPage(pageNumber)
            val pagedResponse = response.body()
            val data = pagedResponse?.results

            var nextPageNumber: Int? = null

            if (pagedResponse?.pageInfo?.next != null) {
                val uri = Uri.parse(pagedResponse.pageInfo.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}

}
