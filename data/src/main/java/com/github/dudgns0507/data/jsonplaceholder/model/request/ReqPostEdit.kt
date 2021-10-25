package com.github.dudgns0507.data.jsonplaceholder.model.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReqPostEdit(
    @SerializedName("title") val title: String = "",
    @SerializedName("body") val body: String = "",
) : Parcelable
