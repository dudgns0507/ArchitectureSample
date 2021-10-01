package com.github.dudgns0507.core.util.ext

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.util.Patterns
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun String.isEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isLink(): Boolean {
    return Patterns.WEB_URL.matcher(this).matches()
}

fun String.isPhone(): Boolean {
    return Patterns.PHONE.matcher(this).matches()
}

val String.containsDigit: Boolean
    get() = matches(Regex(".*[0-9].*"))

val String.isAlpha: Boolean
    get() = matches(Regex("[a-zA-Z0-9]*"))