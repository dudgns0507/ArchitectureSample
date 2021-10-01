package com.github.dudgns0507.core.util.exception

import java.io.IOException

class NetworkStatusException : IOException() {
    override val message: String
        get() = "No Internet Connection"
}