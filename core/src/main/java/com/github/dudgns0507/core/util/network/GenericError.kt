package com.github.dudgns0507.core.util.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenericError(
    @SerializedName("status_code") val statusCode: String,
    @SerializedName("status_message") val statusMessage: String
) : Parcelable
