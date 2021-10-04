package com.github.dudgns0507.domain.dto

data class Post(
    val id: Int = 0,
    val userId: Int = 0,
    val title: String = "",
    val body: String = ""
)

class InvalidPostException(code: Int, message: String): Exception()