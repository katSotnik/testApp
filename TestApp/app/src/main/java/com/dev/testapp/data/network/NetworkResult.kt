package com.dev.testapp.data.network

sealed class NetworkResult<T> {
    class Success<T>(val data: T) : NetworkResult<T>()
    class Error<T>(val message: String? = null, val data: T? = null) : NetworkResult<T>()
}