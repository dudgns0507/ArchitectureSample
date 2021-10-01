package com.github.dudgns0507.core.util.network

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class SafeCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Call::class.java != getRawType(returnType)) {
            return null
        }

        check(returnType is ParameterizedType) { "return type must be parameterized as Call<ResultWrapper<<Foo>> or Call<ResultWrapper<out Foo>>" }

        val responseType = getParameterUpperBound(0, returnType)

        if (getRawType(responseType) != ResultWrapper::class.java) {
            return null
        }

        check(responseType is ParameterizedType) { "Response must be parameterized as ResultWrapper<Foo> or ResultWrapper<out Foo>" }

        val successBodyType = getParameterUpperBound(0, responseType)
        val errorBodyType = getParameterUpperBound(1, responseType)
        val errorBodyConverter = retrofit.nextResponseBodyConverter<Any>(null, errorBodyType, annotations)

        return SafeCallAdapter<Any, Any>(successBodyType, errorBodyConverter)
    }
}