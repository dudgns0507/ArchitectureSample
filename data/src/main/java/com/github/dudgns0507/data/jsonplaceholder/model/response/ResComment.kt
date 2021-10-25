package com.github.dudgns0507.data.jsonplaceholder.model.response

import android.os.Parcelable
import com.github.dudgns0507.domain.dto.Comment
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResComment(
    @SerializedName("postId") val postId: Int = 0,
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("email") val email: String = "",
    @SerializedName("body") val body: String = "",
) : Parcelable {
    fun toModel(): Comment {
        return Comment(
            id = id,
            postId = postId,
            name = name,
            email = email,
            body = body
        )
    }
}
