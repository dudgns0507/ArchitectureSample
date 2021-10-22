package com.github.dudgns0507.core.util.network

import retrofit2.Response

enum class ApiStatus {
    SUCCESS,
    ERROR,
    LOADING
}

sealed class ApiResult <out T> (val status: ApiStatus, val data: T?, val message: String?) {
    data class Success<out R>(val _data: R?) : ApiResult<R>(
        status = ApiStatus.SUCCESS,
        data = _data,
        message = null
    )

    data class Error(val exception: String) : ApiResult<Nothing>(
        status = ApiStatus.ERROR,
        data = null,
        message = exception
    )

    data class Loading<out R>(val isLoading: Boolean) : ApiResult<R>(
        status = ApiStatus.LOADING,
        data = null,
        message = null
    )
}

inline fun Response<*>.isSuccess(call: () -> Unit) {
    if (isSuccessful) {
        call()
    }
}
