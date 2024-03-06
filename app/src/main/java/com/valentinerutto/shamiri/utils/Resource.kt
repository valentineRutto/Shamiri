package com.valentinerutto.shamiri.utils

import okhttp3.internal.http2.ErrorCode


sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
//    data class Error<T>(val errorMessage: String) : Resource<T>()
    data class Error(val error: ErrorCode) : Resource<Nothing>()

    class Loading<T> : Resource<T>()

}