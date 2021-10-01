package com.github.dudgns0507.data.jsonplaceholder.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class ResPost(
    @Json(name = "userId") val userId: Int = 0,
    @Json(name = "id") val id: Int = 0,
    @Json(name = "title") val title: String = "",
    @Json(name = "body") val body: String = "",
) : Parcelable