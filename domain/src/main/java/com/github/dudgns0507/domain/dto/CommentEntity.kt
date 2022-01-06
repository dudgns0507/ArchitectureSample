package com.github.dudgns0507.domain.dto

data class CommentEntity(
    val id: Int = 0,
    val postId: Int = 0,
    val name: String = "",
    val email: String = "",
    val body: String = ""
)
