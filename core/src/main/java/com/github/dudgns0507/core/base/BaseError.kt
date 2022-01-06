package com.github.dudgns0507.core.base

import android.net.Uri

enum class BaseError(
    private val errorCode: Int,
    private val message: String
) {
    CODE_000(0, "Network Error"),
    CODE_001(1, "Network Data Error"),
    CODE_999(999, "Unknown Error");

    override fun toString(): String {
        val url = Uri.parse("")
        url.host
        return "Error Code : ${String.format("%03d", errorCode)} (${
            when (message.isBlank()) {
                true -> "Unknown Error(Need Error Message)"
                false -> message
            }
        })"
    }
}
