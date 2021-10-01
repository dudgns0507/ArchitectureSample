package com.github.dudgns0507.core.util.network

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class GenericError(
    @Json(name = "status_code") val statusCode: String,
    @Json(name = "status_message") val statusMessage: String
) : Parcelable