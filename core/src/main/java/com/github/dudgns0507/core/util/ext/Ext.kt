package com.github.dudgns0507.core.util.ext

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun Any?.isNull(): Boolean {
    return this == null
}

fun String.toDate(format: String, locale: Locale = Locale.getDefault()): Date? {
    val dateFormatter = SimpleDateFormat(format, locale)
    return try {
        dateFormatter.parse(this)
    } catch (e: ParseException) {
        null
    }
}

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val dateFormatter = SimpleDateFormat(format, locale)
    return dateFormatter.format(this)
}

@ExperimentalCoroutinesApi
fun <T> Call<T>.asCallbackFLow() = callbackFlow<T> {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {

            if (response.isSuccessful) {
                response.body()?.let { trySend(it).isSuccess } ?: close()
            } else {
                close()
//                close("${response.code()} + ${response.errorBody()}")
            }
        }

        override fun onFailure(call: Call<T>, throwable: Throwable) {
            close(throwable)
        }
    })

    awaitClose()
}

fun<T> Call<T>.request(
    onResponse: (call: Call<T>, response: Response<T>) -> Unit,
    onFailure: (call: Call<T>, t: Throwable) -> Unit
) {
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            onResponse(call, response)
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            onFailure(call, t)
        }
    })
}