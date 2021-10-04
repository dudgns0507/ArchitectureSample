package com.github.dudgns0507.core.util.network

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Loading<T>(val data: T? = null) : Resource<T>()
    data class Failure(val throwable: Throwable?) : Resource<Nothing>()

//    override fun toString(): String {
//        return when (this) {
//            is Success<*> -> "Success[data=$data]"
//            is DataError -> "Error[exception=$errorCode]"
//            is Loading<T> -> "Loading"
//        }
//    }
}
//
//inline fun <T : Any, U : Any> ResultWrapper<T, U>.updateOnSuccess(
//    liveData: MutableLiveData<T?>,
//    onError: (error: ResultWrapper<T, U>?) -> Unit
//) {
//    when (this) {
//        is ResultWrapper.Success<T> -> {
//            liveData.value = this.value
//        }
//        is ResultWrapper.ApiError,
//        is ResultWrapper.NetworkError,
//        is ResultWrapper.UnknownError -> {
//            onError(this)
//        }
//    }
//}
//
//inline fun <T : Any, U : Any> ResultWrapper<List<T>, U>.appendOnSuccess(
//    liveData: MutableLiveData<List<T>?>,
//    onError: (error: ResultWrapper<List<T>, U>?) -> Unit
//) {
//    when (this) {
//        is ResultWrapper.Success<List<T>> -> {
//            liveData.value?.let {
//                val temp = it.toMutableList()
//                temp.addAll(this.value)
//                liveData.value = temp.toList()
//            }
//        }
//        is ResultWrapper.ApiError,
//        is ResultWrapper.NetworkError,
//        is ResultWrapper.UnknownError -> {
//            onError(this)
//        }
//    }
//}