package com.github.dudgns0507.core.util.ext

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun Any?.isNull(): Boolean {
    return this == null
}

fun String.toDate(format: String, locale: Locale = Locale.getDefault()): Date? {
    val dateFormatter = SimpleDateFormat(format, locale)
    return try {
        dateFormatter.parse(this)
    } catch (e: ParseException) {
        null
    }
}

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val dateFormatter = SimpleDateFormat(format, locale)
    return dateFormatter.format(this)
}