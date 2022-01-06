package com.github.dudgns0507.data.jsonplaceholder.model.response

import android.os.Parcelable
import com.github.dudgns0507.domain.dto.PostEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResPost(
    @SerializedName("userId") val userId: Int = 0,
    @SerializedName("id") val id: Int = 0,
    @SerializedName("title") val title: String = "",
    @SerializedName("body") val body: String = "",
) : Parcelable {
    fun toModel(): PostEntity {
        return PostEntity(
            id = id,
            userId = userId,
            title = title,
            body = body
        )
    }
}
