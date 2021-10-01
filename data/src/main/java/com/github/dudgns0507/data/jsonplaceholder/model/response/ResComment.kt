package com.github.dudgns0507.data.jsonplaceholder.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class ResComment(
    @Json(name = "postId") val postId: Int = 0,
    @Json(name = "id") val id: Int = 0,
    @Json(name = "name") val name: String = "",
    @Json(name = "email") val email: String = "",
    @Json(name = "body") val body: String = "",
) : Parcelable