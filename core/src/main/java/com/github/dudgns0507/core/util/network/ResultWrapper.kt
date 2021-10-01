package com.github.dudgns0507.core.util.network

import androidx.lifecycle.MutableLiveData
import okhttp3.Headers
import java.io.IOException

sealed class ResultWrapper<out T : Any, out U : Any> {

    /**
     * Success response with body
     */
    data class Success<out T : Any>(val headers: Headers, val value: T) : ResultWrapper<T, Nothing>()

    /**
     * Failure response with body
     */
    data class ApiError<U : Any>(val code: Int? = null, val error: U? = null) : ResultWrapper<Nothing, U>()

    /**
     * Network error
     */
    data class NetworkError(val error: IOException) : ResultWrapper<Nothing, Nothing>()

    /**
     * For example, json parsing error
     */
    data class UnknownError(val throwable: Throwable?) : ResultWrapper<Nothing, Nothing>()
}

inline fun <T : Any, U : Any> ResultWrapper<T, U>.updateOnSuccess(
    liveData: MutableLiveData<T?>,
    onError: (error: ResultWrapper<T, U>?) -> Unit
) {
    when (this) {
        is ResultWrapper.Success<T> -> {
            liveData.value = this.value
        }
        is ResultWrapper.ApiError,
        is ResultWrapper.NetworkError,
        is ResultWrapper.UnknownError -> {
            onError(this)
        }
    }
}

inline fun <T : Any, U : Any> ResultWrapper<List<T>, U>.appendOnSuccess(
    liveData: MutableLiveData<List<T>?>,
    onError: (error: ResultWrapper<List<T>, U>?) -> Unit
) {
    when (this) {
        is ResultWrapper.Success<List<T>> -> {
            liveData.value?.let {
                val temp = it.toMutableList()
                temp.addAll(this.value)
                liveData.value = temp.toList()
            }
        }
        is ResultWrapper.ApiError,
        is ResultWrapper.NetworkError,
        is ResultWrapper.UnknownError -> {
            onError(this)
        }
    }
}