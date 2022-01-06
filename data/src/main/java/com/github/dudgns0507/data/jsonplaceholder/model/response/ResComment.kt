package com.github.dudgns0507.data.jsonplaceholder.model.response

import android.os.Parcelable
import com.github.dudgns0507.domain.dto.CommentEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResComment(
    @SerializedName("postId") val postId: Int = 0,
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("email") val email: String = "",
    @SerializedName("body") val body: String = "",
) : Parcelable {
    fun toModel(): CommentEntity {
        return CommentEntity(
            id = id,
            postId = postId,
            name = name,
            email = email,
            body = body
        )
    }
}
