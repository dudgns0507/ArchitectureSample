package com.github.dudgns0507.core.util.network

import com.github.dudgns0507.core.util.exception.NetworkStatusException
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.*
import java.io.IOException

class SafeCall<T : Any, E : Any>(
    private val delegate: Call<T>,
    private val errorConverter: Converter<ResponseBody, E>
) : Call<ResultWrapper<T, E>> {

    override fun enqueue(callback: Callback<ResultWrapper<T, E>>) {
        return delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()
                val headers = response.headers()

                try {
                    body?.let {
                        callback.onResponse(this@SafeCall, Response.success(ResultWrapper.Success(headers = headers, value = it)))
                    } ?: kotlin.run {
                        callback.onResponse(this@SafeCall, Response.success(ResultWrapper.UnknownError(null)))
                    }
                } catch (throwable: Throwable) {
                    callback.onResponse(this@SafeCall, Response.success(getError(code, error, throwable)))
                }
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                callback.onResponse(this@SafeCall, Response.success(getError(throwable)))
            }
        })
    }

    private fun getError(code: Int, error: ResponseBody?, throwable: Throwable): ResultWrapper<T, E> {
        return when (throwable) {
            is HttpException -> {
                val errorBody = when {
                    error == null -> null
                    error.contentLength() == 0L -> null
                    else -> try {
                        errorConverter.convert(error)
                    } catch (ex: Exception) {
                        null
                    }
                }

                errorBody?.let {
                    ResultWrapper.ApiError(code, it)
                } ?: kotlin.run {
                    ResultWrapper.UnknownError(throwable)
                }
            }
            else -> getError(throwable)
        }
    }

    private fun getError(throwable: Throwable): ResultWrapper<T, E> {
        return when (throwable) {
            is NetworkStatusException -> ResultWrapper.NetworkError(throwable)
            is IOException -> ResultWrapper.NetworkError(throwable)
            else -> ResultWrapper.UnknownError(throwable)
        }
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = SafeCall(delegate.clone(), errorConverter)

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<ResultWrapper<T, E>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = timeout()
}