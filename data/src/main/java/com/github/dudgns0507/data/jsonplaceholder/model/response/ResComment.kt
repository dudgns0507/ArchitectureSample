package com.github.dudgns0507.data.jsonplaceholder.model.response

import android.os.Parcelable
import com.github.dudgns0507.domain.dto.Comment
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class ResComment(
    @Json(name = "postId") val postId: Int = 0,
    @Json(name = "id") val id: Int = 0,
    @Json(name = "name") val name: String = "",
    @Json(name = "email") val email: String = "",
    @Json(name = "body") val body: String = "",
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
