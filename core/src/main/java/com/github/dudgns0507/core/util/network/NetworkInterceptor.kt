package com.github.dudgns0507.core.util.network

import android.content.Context
import com.github.dudgns0507.core.util.exception.NetworkStatusException
import com.github.dudgns0507.core.util.ext.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class NetworkInterceptor(val context: Context) : Interceptor {
    private val isConnected: Boolean
        get() = context.isNetworkAvailable()

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected) {
            throw NetworkStatusException()
        }

        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}