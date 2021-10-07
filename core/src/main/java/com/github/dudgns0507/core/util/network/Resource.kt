package com.github.dudgns0507.core.util.network

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Loading<T>(val data: T? = null) : Resource<T>()
    data class Failure(val throwable: Throwable?) : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Loading<T> -> "Loading"
            is Failure -> "Error[exception=$throwable]"
        }
    }
}