package com.github.dudgns0507.domain.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostEntity(
    val id: Int = 0,
    val userId: Int = 0,
    val title: String = "",
    val body: String = ""
) : Parcelable

class InvalidPostException(code: Int, message: String) : Exception()
